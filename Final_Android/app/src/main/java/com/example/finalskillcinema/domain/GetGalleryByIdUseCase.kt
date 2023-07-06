package com.example.finalskillcinema.domain

import com.example.finalskillcinema.data.CinemaRepository
import javax.inject.Inject

class GetGalleryByIdUseCase @Inject constructor(
    private val repository: CinemaRepository
) {
    suspend fun executeGalleryByFilmId(filmId: Int) =
        repository.getFilmGallery(filmId)
}