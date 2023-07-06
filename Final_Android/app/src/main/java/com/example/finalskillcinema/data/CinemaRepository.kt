package com.example.finalskillcinema.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import com.example.finalskillcinema.api.CinemaApi
import com.example.finalskillcinema.api.model.FilmPremier
import com.example.finalskillcinema.api.model.FilmTop
import com.example.finalskillcinema.api.model.convertForDbGenres
import com.example.finalskillcinema.api.model.convertForDbShortInfo
import com.example.finalskillcinema.api.model.FilmByFilter
import com.example.finalskillcinema.api.ResponseFilmDetailById
import com.example.finalskillcinema.api.ResponseGalleryByFilmId
import com.example.finalskillcinema.api.model.ResponsePersonById
import com.example.finalskillcinema.api.model.ResponsePersonsByFilmId
import com.example.finalskillcinema.api.ResponseSeasons
import com.example.finalskillcinema.api.ResponseSimilarFilmsByFilmId
import com.example.finalskillcinema.api.model.convertToDb
import com.example.finalskillcinema.api.convertToDbDetailInfo
import com.example.finalskillcinema.api.model.convertToDbFilmPersons
import com.example.finalskillcinema.api.convertToDbGallery
import com.example.finalskillcinema.api.convertToDbSimilar
import com.example.finalskillcinema.api.convertToShortInfo
import com.example.finalskillcinema.api.old.KinopoiskApi
import com.example.finalskillcinema.api.old.personbyname.ItemPerson
import com.example.finalskillcinema.app.converterInMonth
import com.example.finalskillcinema.db.CinemaDao
import com.example.finalskillcinema.db.model.CollectionFilms
import com.example.finalskillcinema.db.model.FilmImage
import com.example.finalskillcinema.db.model.FilmMarkers
import com.example.finalskillcinema.db.model.FilmPersons
import com.example.finalskillcinema.db.model.FilmWithDetailInfo
import com.example.finalskillcinema.db.model.FilmWithGenres
import com.example.finalskillcinema.db.model.FilmsShortInfo
import com.example.finalskillcinema.db.model.HistoryFilms
import com.example.finalskillcinema.db.model.NewFilmCountries
import com.example.finalskillcinema.db.model.NewFilmGenres
import com.example.finalskillcinema.db.model.NewFilmInCollection
import com.example.finalskillcinema.db.model.NewFilmSimilar
import com.example.finalskillcinema.db.model.NewFilmTopType
import com.example.finalskillcinema.db.model.NewPersonFilms
import com.example.finalskillcinema.db.model.NewSeasonEpisode
import com.example.finalskillcinema.db.model.PersonFilms
import com.example.finalskillcinema.db.model.PersonWithDetailInfo
import com.example.finalskillcinema.db.model.SeasonEpisode
import java.io.IOException
import java.util.Calendar
import javax.inject.Inject

class CinemaRepository @Inject constructor(
    private val apiService: CinemaApi,
    private val apiDatabase: CinemaDao,
    private val oldService: KinopoiskApi
) {
    private val calendar = Calendar.getInstance()

    init {
        kotlinx.coroutines.runBlocking(Dispatchers.IO) {
            apiDatabase.insertCollection(CollectionFilms("Любимые"))
            apiDatabase.insertCollection(CollectionFilms("Хочу посмотреть"))
        }
    }

    suspend fun getFilmsTopByCategoryList(
        categoryName: String,
        page: Int? = 1,
        filters: ParamsFilterFilm = ParamsFilterFilm(),
        year: Int = calendar.get(Calendar.YEAR),
        month: String = (calendar.get(Calendar.MONTH) + 1).converterInMonth()
    ): List<FilmWithGenres> {
        return try {
            apiDatabase.clearFilmTopTypesByCategory(categoryName)
            var genresForDb: List<List<NewFilmGenres>> = emptyList()
            val filmsForDb: List<FilmsShortInfo> = when (categoryName) {
                FilmCategories.PREMIERS.name -> {
                    val response: List<FilmPremier> =
                        apiService.getPremier(year = year, month = month).films
                    if (response.isNotEmpty()) {
                        genresForDb = response.map { it.convertForDbGenres() }
                        response.map { it.convertForDbShortInfo() }
                    } else emptyList()
                }

                FilmCategories.TV_SERIES.name -> {
                    val response: List<FilmByFilter> = apiService.getFilmsByFilter(
                        countries = filters.countries.keys.joinToString(","),
                        genres = filters.genres.keys.joinToString(","),
                        order = filters.order,
                        type = categoryName,
                        ratingFrom = filters.ratingFrom,
                        ratingTo = filters.ratingTo,
                        yearFrom = filters.yearFrom,
                        yearTo = filters.yearTo,
                        imdbId = filters.imdbId,
                        keyword = filters.keyword,
                        page = page!!
                    ).films
                    if (response.isNotEmpty()) {
                        genresForDb = response.map { it.convertForDbGenres() }
                        response.map { it.convertForDbShortInfo() }
                    } else emptyList()
                }

                else -> {
                    val response: List<FilmTop> =
                        apiService.getFilmsTop(type = categoryName, page = page!!).films
                    if (response.isNotEmpty()) {
                        genresForDb = response.map { it.convertForDbGenres() }
                        response.map { it.convertForDbShortInfo() }
                    } else emptyList()
                }
            }
            filmsForDb.forEach { apiDatabase.deleteFilmGenres(it.filmId) }
            genresForDb.forEach { apiDatabase.insertFilmGenres(it) }

            apiDatabase.insertFilmTopTypes(filmsForDb.map {
                NewFilmTopType(filmId = it.filmId, categoryName = categoryName)
            })
            apiDatabase.insertFilmShortInfo(filmsForDb)
            apiDatabase.getFilmsByTopType(categoryName)
        } catch (e: HttpException) {
            apiDatabase.getFilmsByTopType(categoryName)
        } catch (e: IOException) {
            throw IOException(e)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getFilmsTopByCategoryPaging(
        categoryName: String,
    ): Flow<PagingData<FilmWithGenres>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = MediatorFilmTop(
                apiService = apiService,
                apiDatabase = apiDatabase,
                categoryName = categoryName,
                year = calendar.get(Calendar.YEAR),
                month = (calendar.get(Calendar.MONTH) + 1).converterInMonth()
            ),
            pagingSourceFactory = { apiDatabase.getFilmByTopCategoryPaging(categoryName = categoryName) }
        ).flow
    }

    fun getPersonsByName(personName: String): Flow<PagingData<ItemPerson>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { NewPersonsPagingSource(apiService, oldService, personName) }
        ).flow
    }

    suspend fun getDetailInfoByFilm(filmId: Int): FilmWithDetailInfo {
        return try {
            val responseDetail: ResponseFilmDetailById = apiService.getFilmById(filmId)

            apiDatabase.insertOneFilmShortInfo(
                FilmsShortInfo(
                    filmId = responseDetail.kinopoiskId,
                    name = responseDetail.nameRu ?: responseDetail.nameEn
                    ?: responseDetail.nameOriginal ?: "",
                    poster = responseDetail.posterUrl,
                    rating = responseDetail.ratingKinopoisk.toString()
                )
            )

            apiDatabase.clearCountriesByFilm(filmId)
            apiDatabase.clearGalleryByFilm(filmId)
            apiDatabase.cleaFilmPersons(filmId)
            apiDatabase.clearSimilar(filmId)

            apiDatabase.insertFilmDetailInfo(responseDetail.convertToDbDetailInfo())

            val responseFilmPersons: List<ResponsePersonsByFilmId> = apiService.getPersons(filmId)
            apiDatabase.insertFilmPersons(responseFilmPersons.map { it.convertToDbFilmPersons(filmId) })

            if (responseDetail.serial == true) {
                apiDatabase.clearSeriesEpisodes(filmId)

                val responseSeasons: ResponseSeasons = apiService.getSeasons(filmId)

                responseSeasons.seasons.forEach { season ->
                    apiDatabase.insertSeasonEpisodes(season.episodes.map { episode ->
                        NewSeasonEpisode(
                            filmId = filmId,
                            seriesNumber = episode.seasonNumber,
                            episodeNumber = episode.episodeNumber,
                            name = episode.nameRu ?: episode.nameEn,
                            date = episode.releaseDate,
                            synopsis = episode.synopsis
                        )
                    })
                }
            }

            val countriesToDb = responseDetail.countries.map { country ->
                NewFilmCountries(
                    filmId = filmId,
                    country = country.country
                )
            }
            apiDatabase.insertFilmCountries(countriesToDb)

            GALLERY_TYPES.keys.forEach { galleryType ->
                val images: ResponseGalleryByFilmId =
                    apiService.getFilmImages(filmId, galleryType, 1)
                val gallery = images.convertToDbGallery(filmId, galleryType)
                apiDatabase.insertFilmGallery(gallery)
            }

            val responseSimilarFilms: ResponseSimilarFilmsByFilmId =
                apiService.getSimilarFilms(filmId)
            val similarToDb: List<NewFilmSimilar>? = responseSimilarFilms.convertToDbSimilar(filmId)
            if (similarToDb != null) {
                apiDatabase.insertSimilar(similarToDb)
            }

            apiDatabase.getCurrentFilmDetailInfo(filmId)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getPersonsByFilm(personId: Int): List<FilmPersons> {
        return apiDatabase.getAllPersonsByFilm(personId)
    }

    suspend fun getPersonDetailInfo(personId: Int): PersonWithDetailInfo {
        apiDatabase.clearPersonFilms(personId)

        val response: ResponsePersonById = apiService.getPersonById(personId)

        val tempFilms = response.films
        if (tempFilms != null) {
            apiDatabase.insertPersonFilms(tempFilms.map {
                NewPersonFilms(
                    personId = personId,
                    filmId = it.filmId,
                    professionKey = it.professionKey
                )
            })
        }
        apiDatabase.insertOnePersonShortInfo(response.convertToDb())
        return apiDatabase.getPersonWithFilms(personId)
    }

    suspend fun getFilmShortInfo(filmId: Int): FilmsShortInfo {
        val count = apiDatabase.getFilmShortInfoCount(filmId)
        if (count != 0) {
            apiDatabase.getFilmShortInfo(filmId)
        } else {
            val response = apiService.getFilmById(filmId)
            apiDatabase.insertOneFilmShortInfo(response.convertToShortInfo())
        }
        return apiDatabase.getFilmShortInfo(filmId)
    }

    suspend fun getFilmsByPerson(personId: Int): List<PersonFilms> {
        return apiDatabase.getFilmsByPerson(personId)
    }

    suspend fun getFilmGallery(filmId: Int): List<FilmImage> {
        return apiDatabase.getFilmGallery(filmId)
    }

    fun getSeasonsById(seriesId: Int): List<SeasonEpisode> {
        return apiDatabase.getSeriesSeasonsWithEpisodes(seriesId)
    }


    // начал писать, но не реализовал до конца
    // работа с маркерами по фильму
    suspend fun addMarkersToFilm(filmId: Int) {
        apiDatabase.insertFilmMarkers(FilmMarkers(filmId, 0, 0, 0))
    }

    fun getFilmMarkers(filmId: Int): Flow<FilmMarkers> {
        return apiDatabase.getFilmMarkers(filmId)
    }

    fun getAllViewedFilms(): Flow<List<FilmWithGenres>> {
        return apiDatabase.getAllViewedFilms()
    }

    suspend fun updateFilmMarkers(
        filmId: Int,
        isFavorite: Int,
        inCollection: Int,
        isViewed: Int
    ) {
        apiDatabase.updateFilmMarkers(filmId, isFavorite, inCollection, isViewed)
        if (isFavorite == 1) insertFilmInCollection(filmId, "Любимые")
        else apiDatabase.deleteFilmFromCollection(filmId, "Любимые")
    }

    fun getCountFavoriteFilms(): Int {
        return apiDatabase.getCountFavoriteFilms()
    }

    suspend fun insertFilmToHistory(filmId: Int) {
        apiDatabase.insertHistoryFilms(HistoryFilms(filmId))
    }

    fun getAllFilmsHistory(): Flow<List<FilmWithGenres>> {
        return apiDatabase.getAllFilmsHistory()
    }

    suspend fun clearAllHistoryFilms() {
        apiDatabase.clearHistoryFilms()
    }

    suspend fun clearAllViewedFilms() {
        apiDatabase.clearAllFromViewed()
    }

    private suspend fun insertFilmInCollection(filmId: Int, collectionName: String) {
        val isFilmInCollection = apiDatabase.checkFilmInCollection(collectionName, filmId)
        if (isFilmInCollection == 0) apiDatabase.insertFilmInCollection(
            NewFilmInCollection(
                collectionName = collectionName,
                filmId = filmId
            )
        )
    }

    suspend fun addCollection(name: String) {
        apiDatabase.insertCollection(CollectionFilms(name))
    }

    fun getAllCollections(): Flow<List<CollectionFilms>> {
        return apiDatabase.getAllCollections()
    }

    fun getFilmsByCollection(collectionName: String): List<FilmWithGenres> {
        return apiDatabase.getAllFilmInCollections(collectionName)
    }
}