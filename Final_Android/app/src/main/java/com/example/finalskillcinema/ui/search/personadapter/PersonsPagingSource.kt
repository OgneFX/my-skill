package com.example.finalskillcinema.ui.search.personadapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.finalskillcinema.api.old.personbyname.ItemPerson
import com.example.finalskillcinema.domain.GetPersonByNameUseCase

class PersonsPagingSource(
    private val getPersonByNameUseCase: GetPersonByNameUseCase,
    private val personName: String
) : PagingSource<Int, ItemPerson>() {
    override fun getRefreshKey(state: PagingState<Int, ItemPerson>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemPerson> {
        val page = params.key ?: 1
        val pageSize = params.loadSize.coerceAtMost(20)
        return kotlin.runCatching {
            getPersonByNameUseCase.executePerson(personName, page).items ?: emptyList()
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = if (it.size < pageSize) null else page + 1,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )
    }

    private companion object {
        private const val FIRST_PAGE = 1
    }
}