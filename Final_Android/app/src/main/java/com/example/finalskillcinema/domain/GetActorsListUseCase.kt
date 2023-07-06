package com.example.finalskillcinema.domain

import com.example.finalskillcinema.data.CinemaRepository
import com.example.finalskillcinema.db.model.FilmPersons
import javax.inject.Inject

class GetActorsListUseCase @Inject constructor(
    private val newRepository: CinemaRepository
) {
    suspend fun executePersonsList(filmId: Int): List<FilmPersons> {
        return newRepository.getPersonsByFilm(filmId)
    }
}