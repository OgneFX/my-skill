package com.example.finalskillcinema.domain

import androidx.paging.PagingData
import com.example.finalskillcinema.data.ParamsFilterFilm
import com.example.finalskillcinema.db.model.FilmWithGenres
import kotlinx.coroutines.flow.Flow
import com.example.finalskillcinema.data.CinemaRepository
import javax.inject.Inject

class GetTopFilmsUseCase @Inject constructor(
    private val repository: CinemaRepository
) {

    suspend fun executeTopFilms(
        topType: String,
        page: Int? = 1,
        filters: ParamsFilterFilm = ParamsFilterFilm(),
    ): List<FilmWithGenres> {
        return repository.getFilmsTopByCategoryList(topType, page, filters)
    }

    fun executeTopFilmsPaging(categoryName: String): Flow<PagingData<FilmWithGenres>> {
        return repository.getFilmsTopByCategoryPaging(categoryName)
    }
}