package com.example.finalskillcinema.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Таблица со списком актёров (съёмочной группы) по каждому фильму
@Entity(tableName = "person_short_info")
data class PersonShortInfo(
    @PrimaryKey
    @ColumnInfo(name = "id") val personId: Int,
    @ColumnInfo(name = "person_name") val name: String,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "profession") val profession: String?
)

// Таблица с фильмами по каждой персоне (подключается к таблице PERSONS_SHORT_INFO)
@Entity(tableName = "person_films")
data class PersonFilms(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "person_id") val personId: Int,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "profession_key") val professionKey: String?
)
data class NewPersonFilms(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "person_id") val personId: Int,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "profession_key") val professionKey: String?
)