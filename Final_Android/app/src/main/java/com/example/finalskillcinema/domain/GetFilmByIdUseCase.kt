package com.example.finalskillcinema.domain

import com.example.finalskillcinema.data.CinemaRepository
import com.example.finalskillcinema.db.model.FilmWithDetailInfo
import javax.inject.Inject

class GetFilmByIdUseCase @Inject constructor(
    private val repository: CinemaRepository
) {
    suspend fun executeFilmDetailInfoById(filmId: Int): FilmWithDetailInfo {
        return repository.getDetailInfoByFilm(filmId)
    }
}