package com.example.finalskillcinema.data

import com.example.finalskillcinema.api.old.KinopoiskApi
import com.example.finalskillcinema.api.old.filmbyfilter.ResponseByFilter
import com.example.finalskillcinema.api.old.filmbyfilter.ResponseGenresCountries
import com.example.finalskillcinema.api.old.personbyname.ResponsePersonByName
import javax.inject.Inject

class RepositoryAPI @Inject constructor(
    private val apiService: KinopoiskApi
) {
    // FragmentSearch
    suspend fun getFilmsByFilter(filters: ParamsFilterFilm, page: Int): ResponseByFilter {
        return apiService.getFilmsByFilter(
            countries = if (filters.countries.isNotEmpty()) filters.countries.keys.first()
                .toString() else "",
            genres = if (filters.genres.isNotEmpty()) filters.genres.keys.first()
                .toString() else "",
            order = filters.order,
            type = filters.type,
            ratingFrom = filters.ratingFrom,
            ratingTo = filters.ratingTo,
            yearFrom = filters.yearFrom,
            yearTo = filters.yearTo,
            imdbId = filters.imdbId,
            keyword = filters.keyword,
            page = page
        )
    }

    suspend fun getGenresCountries(): ResponseGenresCountries = apiService.getGenresCountries()

    suspend fun getPersonByName(personName: String, page: Int): ResponsePersonByName {
        return apiService.getPersonByName(personName, page)
    }
}