package com.example.finalskillcinema.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collections")
data class CollectionFilms(
    @PrimaryKey
    @ColumnInfo(name = "collection_name") val collectionName: String
)

@Entity(tableName = "films_history")
data class HistoryFilms(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int
)

@Entity(tableName = "film_in_collection")
data class FilmInCollection(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "collection_name") val collectionName: String,
    @ColumnInfo(name = "film_id") val filmId: Int
)
data class NewFilmInCollection(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "collection_name") val collectionName: String,
    @ColumnInfo(name = "film_id") val filmId: Int
)