package com.example.finalskillcinema.api.old.personbyname

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemPerson(
    @Json(name = "kinopoiskId") val kinopoiskId: Int,
    @Json(name = "nameEn") val nameEn: String,
    @Json(name = "nameRu") val nameRu: String,
    @Json(name = "posterUrl") val posterUrl: String,
    @Json(name = "sex") val sex: String,
    @Json(name = "webUrl") val webUrl: String
)