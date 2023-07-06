package com.example.finalskillcinema.domain

import com.example.finalskillcinema.data.RepositoryAPI
import com.example.finalskillcinema.api.old.filmbyfilter.ResponseGenresCountries
import javax.inject.Inject

class GetGenresCountriesUseCase @Inject constructor(private val repositoryAPI: RepositoryAPI) {
    suspend fun executeGenresCountries(): ResponseGenresCountries {
        return repositoryAPI.getGenresCountries()
    }
}