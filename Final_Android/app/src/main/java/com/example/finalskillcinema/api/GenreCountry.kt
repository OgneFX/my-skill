package com.example.finalskillcinema.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseGenresCountries(
    @Json(name = "countries") val countries: List<FilterCountry>,
    @Json(name = "genres") val genres: List<FilterGenre>
)

@JsonClass(generateAdapter = true)
data class FilterCountry(
    @Json (name = "id") val id: Int,
    @Json (name = "country") val name: String
)

@JsonClass(generateAdapter = true)
data class FilterGenre(
    @Json (name = "id") val id: Int,
    @Json(name = "genre") val name: String
)

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "genre") val genre: String
)

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "country") val country: String
)