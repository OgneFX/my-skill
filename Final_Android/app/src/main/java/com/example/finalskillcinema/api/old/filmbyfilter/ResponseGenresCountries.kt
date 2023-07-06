package com.example.finalskillcinema.api.old.filmbyfilter

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseGenresCountries(
    @Json(name = "countries") val countries: List<FilterCountry>,
    @Json(name = "genres") val genres: List<FilterGenre>
)