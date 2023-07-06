package com.example.finalskillcinema.data

import com.example.finalskillcinema.db.old.FilmDao
import com.example.finalskillcinema.db.old.FilmsCache
import javax.inject.Inject

class RepositoryDB @Inject constructor(private val filmDao: FilmDao) {
    suspend fun clearViewedCollection() = filmDao.clearViewedCollection()
    fun getAllCache() = filmDao.getAllCacheFilms()
    suspend fun clearCache(films: List<FilmsCache>) = filmDao.clearCacheTable(films)
    fun getViewedFilms() = filmDao.getViewedFilms()
    fun getFromCache() = filmDao.getFilmsDetailsFromCache()
}