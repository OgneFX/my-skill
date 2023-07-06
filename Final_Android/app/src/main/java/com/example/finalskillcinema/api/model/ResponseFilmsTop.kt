package com.example.finalskillcinema.api.model

import com.example.finalskillcinema.api.Genre
import com.example.finalskillcinema.db.model.FilmsShortInfo
import com.example.finalskillcinema.db.model.NewFilmGenres
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.math.roundToInt

@JsonClass(generateAdapter = true)
data class ResponseFilmsTop(
    @Json(name = "films") val films: List<FilmTop>,
    @Json(name = "pagesCount") val page: Int
)

@JsonClass(generateAdapter = true)
data class FilmTop(
    @Json(name = "filmId") val filmId: Int,
    @Json(name = "nameRu") val nameRu: String,
    @Json(name = "nameEn") val nameEn: String?,
    @Json(name = "posterUrlPreview") val posterUrlPreview: String,
    @Json(name = "posterUrl") val posterUrl: String,
    @Json(name = "genres") val genres: List<Genre>,
    @Json(name = "rating") val rating: String?,
    @Json(name = "ratingChange") val ratingChange: Any?,
    @Json(name = "ratingVoteCount") val ratingVoteCount: Int
)

fun FilmTop.convertForDbShortInfo(): FilmsShortInfo {
    val rating = if (this.rating != null) {
        if (this.rating.contains("%")) {
            (this.rating.removeSuffix("%").toFloat().roundToInt() * 10 / 100.0).toString()
        } else this.rating
    } else null

    return FilmsShortInfo(
        filmId = this.filmId,
        name = if (this.nameRu.isNotEmpty()) this.nameRu else if (!this.nameEn.isNullOrEmpty()) this.nameEn else "",
        poster = this.posterUrlPreview,
        rating = rating
    )
}
fun FilmTop.convertForDbGenres(): List<NewFilmGenres> {
    return this.genres.map { genre ->
        NewFilmGenres(
            filmId = this.filmId,
            genre = genre.genre
        )
    }
}