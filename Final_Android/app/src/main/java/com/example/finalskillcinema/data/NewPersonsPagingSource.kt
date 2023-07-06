package com.example.finalskillcinema.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.finalskillcinema.api.CinemaApi
import com.example.finalskillcinema.api.old.KinopoiskApi
import com.example.finalskillcinema.api.old.personbyname.ItemPerson

class NewPersonsPagingSource(
    private val apiService: CinemaApi,
    private val oldService: KinopoiskApi,
    private val personName: String
) : PagingSource<Int, ItemPerson>() {
    override fun getRefreshKey(state: PagingState<Int, ItemPerson>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemPerson> {
        val page = params.key ?: 1
        val pageSize = params.loadSize.coerceAtMost(20)
        return kotlin.runCatching {
//            val result = apiService.getPersonByName(personName, page).persons
            val result = oldService.getPersonByName(personName, page).items
            Log.d(TAG, "$personName - ${result.joinToString(" | ") { it.nameRu }}")
            result
        }.fold(
            onSuccess = {
                Log.d(TAG, "load: ${it.joinToString(" | ") { person -> person.nameRu }}")
                LoadResult.Page(
                    data = it,
                    prevKey = if (it.size < pageSize) null else page + 1,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                Log.d(TAG, "load onFailure: ${it.message}")
                LoadResult.Error(it)
            }
        )
    }

    private companion object {
        private const val TAG = "NewPersonsPagingSource"

        private const val FIRST_PAGE = 1
    }
}