package com.example.finalskillcinema.api.model

import com.example.finalskillcinema.api.Country
import com.example.finalskillcinema.api.Genre
import com.example.finalskillcinema.db.model.FilmsShortInfo
import com.example.finalskillcinema.db.model.NewFilmGenres
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



@JsonClass(generateAdapter = true)
data class ResponseFilmsByFilter(
    @Json(name = "total") val total: Int,
    @Json(name = "totalPages") val totalPages: Int,
    @Json(name = "items") val films: List<FilmByFilter>
)

@JsonClass(generateAdapter = true)
data class FilmByFilter(
    @Json(name = "kinopoiskId") val filmId: Int,
    @Json(name = "imdbId") val imdbId: String?,
    @Json(name = "nameRu") val nameRu: String?,
    @Json(name = "nameEn") val nameEn: Any?,
    @Json(name = "nameOriginal") val nameOriginal: String?,
    @Json(name = "posterUrlPreview") val posterPreview: String,
    @Json(name = "posterUrl") val posterUrl: String,
    @Json(name = "countries") val countries: List<Country>,
    @Json(name = "genres") val genres: List<Genre>,
    @Json(name = "ratingKinopoisk") val ratingKinopoisk: Double?,
    @Json(name = "ratingImdb") val ratingImdb: Double?,
    @Json(name = "type") val type: String,
    @Json(name = "year") val year: Int?
)

fun FilmByFilter.convertForDbShortInfo(): FilmsShortInfo {
    return FilmsShortInfo(
        filmId = this.filmId,
        name = nameRu ?: nameEn?.toString() ?: nameOriginal ?: "",
        poster = this.posterPreview,
        rating = (ratingKinopoisk ?: ratingImdb).toString()
    )
}

fun FilmByFilter.convertForDbGenres(): List<NewFilmGenres> {
    return this.genres.map { genre ->
        NewFilmGenres(
            filmId = this.filmId,
            genre = genre.genre
        )
    }
}