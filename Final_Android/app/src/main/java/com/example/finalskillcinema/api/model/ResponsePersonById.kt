package com.example.finalskillcinema.api.model

import com.example.finalskillcinema.db.model.PersonShortInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ResponsePersonById(
    @Json(name = "personId") val personId: Int,
    @Json(name = "nameRu") val nameRu: String?,
    @Json(name = "nameEn") val nameEn: String?,
    @Json(name = "posterUrl") val posterUrl: String,
    @Json(name = "profession") val profession: String?,
    @Json(name = "films") val films: List<FilmsByPerson>?,
//    @Json(name = "webUrl") val webUrl: String?,
//    @Json(name = "birthday") val birthday: String?,
//    @Json(name = "death") val death: String?,
//    @Json(name = "age") val age: Int?,
//    @Json(name = "facts") val facts: List<String>,

//    @Json(name = "sex") val sex: String,
//    @Json(name = "growth") val growth: Int?,
//    @Json(name = "birthplace") val birthPlace: String?,
//    @Json(name = "deathplace") val deathPlace: String?,
//    @Json(name = "hasAwards") val hasAwards: Int?,
//    @Json(name = "spouses") val spouses: List<StaffsSpouses>?
)

@JsonClass(generateAdapter = true)
data class FilmsByPerson(
    @Json(name = "filmId") val filmId: Int,
    @Json(name = "nameRu") val nameRu: String?,
    @Json(name = "nameEn") val nameEn: String?,
    @Json(name = "rating") val rating: String?,
    @Json(name = "professionKey") val professionKey: String?,
    @Json(name = "general") val general: Boolean,
//    @Json(name = "description") val description: String?
)

//@JsonClass(generateAdapter = true)
//data class StaffsSpouses(
//    @Json(name = "personId") val personId: Int,
//    @Json(name = "name") val name: String?,
//    @Json(name = "divorced") val divorced: Boolean,
//    @Json(name = "divorcedReason") val divorcedReason: String?,
//    @Json(name = "sex") val sex: String,
//    @Json(name = "children") val children: Int,
//    @Json(name = "webUrl") val webUrl: String,
//    @Json(name = "relation") val relation: String
//)

fun ResponsePersonById.convertToDb(): PersonShortInfo {
    return PersonShortInfo(
        personId = personId,
        name = nameRu ?: nameEn ?: "",
        poster = posterUrl,
        profession = profession
    )
}