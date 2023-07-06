package com.example.finalskillcinema.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponsePersonByName(
    @Json(name = "items") val persons: List<ItemPerson>,
    @Json(name = "total") val total: Int
)

@JsonClass(generateAdapter = true)
data class ItemPerson(
    @Json(name = "kinopoiskId") val personId: Int,
    @Json(name = "nameRu") val nameRu: String,
    @Json(name = "nameEn") val nameEn: String,
    @Json(name = "posterUrl") val posterUrl: String,
//    @Json(name = "sex") val sex: String,                    // не нужно
//    @Json(name = "webUrl") val webUrl: String               // не нужно
)