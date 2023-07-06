package com.example.finalskillcinema.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films_short_info")
data class FilmsShortInfo(
    @PrimaryKey
    @ColumnInfo(name = "id") val filmId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "rating") val rating: String?
)

@Entity(tableName = "film_top_type")
data class FilmTopType(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "category_name") val categoryName: String
)
data class NewFilmTopType(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "category_name") val categoryName: String
)