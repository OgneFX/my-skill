package com.example.finalskillcinema.db.old

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films_cache")
data class FilmsCache(
    @PrimaryKey
    @ColumnInfo(name = "film_cache_id") val filmId: Int
)

@Entity(tableName = "films_detail")
data class FilmInDB(
    @PrimaryKey
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "description_short") val descriptionShort: String?,
    @ColumnInfo(name = "description_full") val descriptionFull: String?,
    @ColumnInfo(name = "rating") val rating: String?,                       // was Double?
    @ColumnInfo(name = "age_limit") val ageLimit: String?,
    @ColumnInfo(name = "year") val year: Int?,
    @ColumnInfo(name = "length") val filmLength: Int?,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "countries") val countries: String,                  // was List<Country>
    @ColumnInfo(name = "genres") val genres: String,                        // was List<Genre>
    @ColumnInfo(name = "start_year") val startYear: Int?,
    @ColumnInfo(name = "end_year") val endYear: Int?,
    @ColumnInfo(name = "is_serial") val serial: Int,                        // was Boolean
    @ColumnInfo(name = "is_film_favorite") val isFilmFavorite: Int,
    @ColumnInfo(name = "is_film_viewed") val isFilmViewed: Int,
    @ColumnInfo(name = "is_film_in_bookmark") val isFilmInBookmark: Int,
    @Embedded val inCollection: FilmInCollections
)

@Entity(tableName = "films_collections")
data class CollectionsDB(
    @PrimaryKey
    @ColumnInfo(name = "collection_id") val filmId: Int,
    @ColumnInfo(name = "collection_name") val name: String
)

//@Entity(tableName = "films_in_collections")
data class FilmInCollections(
    @PrimaryKey
    @ColumnInfo(name = "collection_id") val id: Int,
    @ColumnInfo(name = "collection_name") val collectionName: String,
    @ColumnInfo(name = "collection_films_film_id") val filmId: Int
)