package com.example.finalskillcinema.api.old.personbyname

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponsePersonByName(
    @Json(name = "items") val items: List<ItemPerson>,
    @Json(name = "total") val total: Int
)