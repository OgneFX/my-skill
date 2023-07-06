package com.example.finalskillcinema.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films_gallery")
data class FilmImage(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "preview") val preview: String,
    @ColumnInfo(name = "category") val imageCategory: String
)
data class NewFilmImage(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "preview") val preview: String,
    @ColumnInfo(name = "category") val imageCategory: String
)