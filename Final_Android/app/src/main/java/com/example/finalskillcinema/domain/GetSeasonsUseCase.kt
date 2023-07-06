package com.example.finalskillcinema.domain

import com.example.finalskillcinema.data.CinemaRepository
import javax.inject.Inject

class GetSeasonsUseCase @Inject constructor(private val repository: CinemaRepository) {
    fun executeSeasons(seriesId: Int) = repository.getSeasonsById(seriesId)
}