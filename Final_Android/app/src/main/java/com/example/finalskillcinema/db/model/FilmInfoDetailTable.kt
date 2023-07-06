package com.example.finalskillcinema.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films_detail_info")
data class FilmDetailInfo(
    @PrimaryKey
    @ColumnInfo(name = "id") val filmId: Int,
    @ColumnInfo(name = "year") val year: Int?,
    @ColumnInfo(name = "length") val length: Int?,
    @ColumnInfo(name = "description_short") val shortDescription: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "age_limit") val ageLimit: String?,
    @ColumnInfo(name = "year_start") val startYear: Int?,
    @ColumnInfo(name = "year_end") val endYear: Int?,
    @ColumnInfo(name = "is_serial") val serial: Int?
)

@Entity(tableName = "film_persons")
data class FilmPersons(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "person_id") val personId: Int,
    @ColumnInfo(name = "profession_key") val professionKey: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "person_name") val name: String,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "profession") val profession: String?
)

data class NewFilmPersons(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "person_id") val personId: Int,
    @ColumnInfo(name = "profession_key") val professionKey: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "person_name") val name: String,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "profession") val profession: String?
)

@Entity(tableName = "film_similar")
data class FilmSimilar(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "similar_id") val similarId: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "preview") val posterPreview: String
)

data class NewFilmSimilar(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "similar_id") val similarId: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "preview") val posterPreview: String
)