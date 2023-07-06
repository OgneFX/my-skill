package com.example.finalskillcinema.domain

import kotlinx.coroutines.flow.Flow
import com.example.finalskillcinema.data.CinemaRepository
import com.example.finalskillcinema.db.model.CollectionFilms
import com.example.finalskillcinema.db.model.FilmWithGenres
import javax.inject.Inject

class GetFilmsHistoryUseCase @Inject constructor(
    private val repository: CinemaRepository
) {
    suspend fun addFilmToHistory(filmId: Int) {
        repository.insertFilmToHistory(filmId)
    }

    fun executeAllHistoryFilms(): Flow<List<FilmWithGenres>> {
        return repository.getAllFilmsHistory()
    }

    suspend fun clearHistoryFilms() {
        repository.clearAllHistoryFilms()
    }

    suspend fun clearViewedFilms() {
        repository.clearAllViewedFilms()
    }

    fun executeAllViewedFilms(): Flow<List<FilmWithGenres>> {
        return repository.getAllViewedFilms()
    }

    fun executeCountFavoriteFilms(): Int {
        return repository.getCountFavoriteFilms()
    }

    fun executeCollectionsList(): Flow<List<CollectionFilms>> {
        return repository.getAllCollections()
    }

    fun executeFilmsByCollection(collectionName: String): List<FilmWithGenres> {
        return repository.getFilmsByCollection(collectionName)
    }

    suspend fun addNewCollection(collectionName: String) {
        return repository.addCollection(name = collectionName)
    }
}