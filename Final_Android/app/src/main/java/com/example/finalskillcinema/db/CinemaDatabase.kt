package com.example.finalskillcinema.db

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.finalskillcinema.db.model.CollectionFilms
import com.example.finalskillcinema.db.model.FilmCountries
import com.example.finalskillcinema.db.model.FilmDetailInfo
import com.example.finalskillcinema.db.model.FilmGenres
import com.example.finalskillcinema.db.model.FilmInCollection
import com.example.finalskillcinema.db.model.FilmPersons
import com.example.finalskillcinema.db.model.FilmSimilar
import com.example.finalskillcinema.db.model.FilmsShortInfo
import com.example.finalskillcinema.db.model.FilmImage
import com.example.finalskillcinema.db.model.FilmMarkers
import com.example.finalskillcinema.db.model.FilmTopType
import com.example.finalskillcinema.db.model.HistoryFilms
import com.example.finalskillcinema.db.model.PersonFilms
import com.example.finalskillcinema.db.model.PersonShortInfo
import com.example.finalskillcinema.db.model.SeasonEpisode

@Database(
    entities = [
        FilmsShortInfo::class,
        FilmDetailInfo::class,
        FilmPersons::class,
        FilmImage::class,
        FilmGenres::class,
        FilmCountries::class,
        PersonShortInfo::class,
        PersonFilms::class,
        CollectionFilms::class,
        FilmInCollection::class,
        FilmSimilar::class,
        SeasonEpisode::class,
        FilmMarkers::class,
        HistoryFilms::class,
        FilmTopType::class
    ],
    version = 1
)
abstract class CinemaDatabase : RoomDatabase() {
    abstract fun cinemaDao(): CinemaDao
}