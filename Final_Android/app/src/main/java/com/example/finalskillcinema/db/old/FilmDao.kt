package com.example.finalskillcinema.db.old

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    // ---------- TABLE_FILMS_DETAIL ----------

    // update is_favorite, is_bookmark, is_viewed
//    @Query("UPDATE films_detail SET is_film_favorite = :favorite, is_film_viewed = :showed, is_film_in_bookmark = :bookmark WHERE film_id = :filmId")
//    suspend fun updateMarkers(filmId: Int, favorite: Int, bookmark: Int, showed: Int)

    // clear IS_FILM_VIEWED
    @Query("UPDATE films_detail SET is_film_viewed = 0")
    suspend fun clearViewedCollection()

    // ---------- TABLE_CACHE ----------
    // get all
    @Query("SELECT * FROM films_cache")
    fun getAllCacheFilms(): Flow<List<FilmsCache>>

    // insert FILM_ID
    @Insert(entity = FilmsCache::class)
    suspend fun insertFilmCache(films: FilmsCache)

    @Delete
    suspend fun clearCacheTable(filmsId: List<FilmsCache>)

    // ---------- FragmentFilmDetail ----------
    // table FILMS_DETAIL get FILM (current)
    @Query("SELECT * FROM films_detail WHERE film_id = :filmId")
    fun getFilmByIdFromDB(filmId: Int): Flow<FilmInDB>

    // ---------- FragmentProfile ----------
    // table FILMS get VIEWED_FILMS
    @Query("SELECT * FROM films_detail WHERE is_film_viewed = 1")
    fun getViewedFilms(): Flow<List<FilmInDB>>

    // tables FILMS_CACHE & FILM_DETAIL get ALL_FILMS
    @Query("SELECT * FROM films_detail, films_cache WHERE film_id = film_cache_id")
    fun getFilmsDetailsFromCache(): Flow<List<FilmInDB>>
}