package com.example.finalskillcinema.api.model

import com.example.finalskillcinema.db.model.NewFilmPersons
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ResponsePersonsByFilmId(
    @Json(name = "staffId") val staffId: Int,
    @Json(name = "nameRu") val nameRu: String?,
    @Json(name = "nameEn") val nameEn: String?,
    @Json(name = "posterUrl") val posterUrl: String,
    @Json(name = "professionKey") val professionKey: String,
    @Json(name = "professionText") val professionText: String,
    @Json(name = "description") val description: String?
)

fun ResponsePersonsByFilmId.convertToDbFilmPersons(filmId: Int): NewFilmPersons {
    return NewFilmPersons(
        filmId = filmId,
        personId = staffId,
        professionKey = this.professionKey,
        description = this.description,
        name = this.nameRu ?: this.nameEn ?: "",
        poster = this.posterUrl,
        profession = this.professionKey
    )
}