package com.example.finalskillcinema.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Таблица с жанрами по каждому фильму (подключается к таблице FILMS_SHORT_INFO)
@Entity(tableName = "film_genres")
data class FilmGenres(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "genre") val genre: String
)
data class NewFilmGenres(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "genre") val genre: String
)

// Таблица со странами по каждому фильму (подключается к таблице FILMS_SHORT_INFO)
@Entity(tableName = "film_countries")
data class FilmCountries(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "country") val country: String
)
data class NewFilmCountries(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "country") val country: String
)