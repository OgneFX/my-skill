package com.example.finalskillcinema.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.example.finalskillcinema.api.old.personbyname.ItemPerson
import com.example.finalskillcinema.data.RepositoryAPI
import com.example.finalskillcinema.api.old.personbyname.ResponsePersonByName
import com.example.finalskillcinema.data.CinemaRepository
import javax.inject.Inject

class GetPersonByNameUseCase @Inject constructor(
    private val repositoryAPI: RepositoryAPI,
    private val repository: CinemaRepository
) {

    suspend fun executePerson(personName: String, page: Int): ResponsePersonByName {
        return repositoryAPI.getPersonByName(personName, page)
    }

    fun newExecutePerson(personName: String): Flow<PagingData<ItemPerson>> {
        return repository.getPersonsByName(personName)
    }
}