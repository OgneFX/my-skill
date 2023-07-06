package com.example.finalskillcinema.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_markers")
data class FilmMarkers(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "is_favorite") val isFavorite: Int,
    @ColumnInfo(name = "is_viewed") val isViewed: Int,
    @ColumnInfo(name = "in_collection") val inCollection: Int
)