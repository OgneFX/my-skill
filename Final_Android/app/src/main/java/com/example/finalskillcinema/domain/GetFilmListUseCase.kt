package com.example.finalskillcinema.domain

import com.example.finalskillcinema.api.old.filmbyfilter.ResponseByFilter
import com.example.finalskillcinema.data.ParamsFilterFilm
import com.example.finalskillcinema.data.RepositoryAPI
import javax.inject.Inject

class GetFilmListUseCase @Inject constructor(private val repositoryAPI: RepositoryAPI) {

    suspend fun executeFilmsByFilterCount(
        filters: ParamsFilterFilm,
        page: Int
    ): ResponseByFilter {
        return repositoryAPI.getFilmsByFilter(filters, page)
    }
}