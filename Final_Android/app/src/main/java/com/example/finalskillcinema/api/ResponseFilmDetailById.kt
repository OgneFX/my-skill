package com.example.finalskillcinema.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.finalskillcinema.db.model.FilmDetailInfo
import com.example.finalskillcinema.db.model.FilmsShortInfo
import com.example.finalskillcinema.db.model.NewFilmImage
import com.example.finalskillcinema.db.model.NewFilmSimilar

@JsonClass(generateAdapter = true)
data class ResponseFilmDetailById(
    @Json(name = "kinopoiskId") val kinopoiskId: Int,
    @Json(name = "imdbId") val imdbId: String?,
    @Json(name = "nameRu") val nameRu: String?,
    @Json(name = "nameEn") val nameEn: String?,
    @Json(name = "nameOriginal") val nameOriginal: String?,
    @Json(name = "posterUrl") val posterUrl: String,
    @Json(name = "posterUrlPreview") val posterUrlPreview: String,
    @Json(name = "coverUrl") val coverUrl: String?,
    @Json(name = "logoUrl") val logoUrl: String?,
    @Json(name = "reviewsCount") val reviewsCount: Int,
    @Json(name = "ratingAwait") val ratingAwait: Double?,
    @Json(name = "ratingAwaitCount") val ratingAwaitCount: Int?,
    @Json(name = "ratingFilmCritics") val ratingFilmCritics: Double?,
    @Json(name = "ratingFilmCriticsVoteCount") val ratingFilmCriticsVoteCount: Int?,
    @Json(name = "ratingGoodReview") val ratingGoodReview: Double?,
    @Json(name = "ratingGoodReviewVoteCount") val ratingGoodReviewVoteCount: Int?,
    @Json(name = "ratingImdb") val ratingImdb: Double?,
    @Json(name = "ratingImdbVoteCount") val ratingImdbVoteCount: Int?,
    @Json(name = "ratingKinopoisk") val ratingKinopoisk: Double?,
    @Json(name = "ratingKinopoiskVoteCount") val ratingKinopoiskVoteCount: Int?,
    @Json(name = "ratingRfCritics") val ratingRfCritics: Double?,
    @Json(name = "ratingRfCriticsVoteCount") val ratingRfCriticsVoteCount: Int?,
    @Json(name = "webUrl") val webUrl: String,
    @Json(name = "year") val year: Int?,
    @Json(name = "filmLength") val filmLength: Int?,
    @Json(name = "slogan") val slogan: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "shortDescription") val shortDescription: String?,
    @Json(name = "editorAnnotation") val editorAnnotation: String?,
    @Json(name = "isTicketsAvailable") val isTicketsAvailable: Boolean,
    @Json(name = "productionStatus") val productionStatus: String?,
    @Json(name = "type") val type: String,
    @Json(name = "ratingMpaa") val ratingMpaa: String?,
    @Json(name = "ratingAgeLimits") val ratingAgeLimits: String?,
    @Json(name = "hasImax") val hasImax: Boolean?,
    @Json(name = "has3D") val has3D: Boolean?,
    @Json(name = "lastSync") val lastSync: String,
    @Json(name = "countries") val countries: List<Country>,
    @Json(name = "genres") val genres: List<Genre>,
    @Json(name = "startYear") val startYear: Int?,
    @Json(name = "endYear") val endYear: Int?,
    @Json(name = "serial") val serial: Boolean?,
    @Json(name = "shortFilm") val shortFilm: Boolean?,
    @Json(name = "completed") val completed: Boolean?
)

@JsonClass(generateAdapter = true)
data class ResponseSimilarFilmsByFilmId(
    @Json(name = "items") val films: List<SimilarItem>?,
    @Json(name = "total") val total: Int
)

@JsonClass(generateAdapter = true)
data class SimilarItem(
    @Json(name = "filmId") val filmId: Int,
    @Json(name = "nameRu") val nameRu: String,
    @Json(name = "nameEn") val nameEn: String?,
    @Json(name = "nameOriginal") val nameOriginal: String?,
    @Json(name = "posterUrl") val posterUrl: String,
    @Json(name = "posterUrlPreview") val posterUrlPreview: String,
    @Json(name = "relationType") val relationType: String
)

@JsonClass(generateAdapter = true)
data class ResponseSeasons(
    @Json(name = "items") val seasons: List<Season>,
    @Json(name = "total") val total: Int
)

@JsonClass(generateAdapter = true)
data class Season(
    @Json(name = "number") val number: Int,
    @Json(name = "episodes") val episodes: List<Episode>
)

@JsonClass(generateAdapter = true)
data class Episode(
    @Json(name = "seasonNumber") val seasonNumber: Int,
    @Json(name = "episodeNumber") val episodeNumber: Int,
    @Json(name = "nameRu") val nameRu: String?,
    @Json(name = "nameEn") val nameEn: String?,
    @Json(name = "releaseDate") val releaseDate: String?,
    @Json(name = "synopsis") val synopsis: String?
)

@JsonClass(generateAdapter = true)
data class ResponseGalleryByFilmId(
    @Json(name = "items") val images: List<ItemImageGallery>,
    @Json(name = "total") val total: Int,
    @Json(name = "totalPages") val totalPages: Int
)

@JsonClass(generateAdapter = true)
data class ItemImageGallery(
    @Json(name = "imageUrl") val image: String,
    @Json(name = "previewUrl") val preview: String
)

fun ResponseFilmDetailById.convertToShortInfo(): FilmsShortInfo {
    return FilmsShortInfo(
        filmId = this.kinopoiskId,
        name = this.nameRu ?: this.nameEn ?: this.nameOriginal ?: "",
        poster = this.posterUrlPreview,
        rating = this.ratingKinopoisk.toString(),
    )
}

fun ResponseFilmDetailById.convertToDbDetailInfo(): FilmDetailInfo {
    return FilmDetailInfo(
        filmId = this.kinopoiskId,
        year = this.year,
        length = this.filmLength,
        shortDescription = this.shortDescription,
        description = this.description,
        type = this.type,
        ageLimit = this.ratingAgeLimits,
        startYear = this.startYear,
        endYear = this.endYear,
        serial = if (this.serial == true) 1 else 0
    )
}

fun ResponseGalleryByFilmId.convertToDbGallery(filmId: Int, type: String): List<NewFilmImage> {
    return this.images.map { image ->
        NewFilmImage(
            filmId = filmId,
            image = image.image,
            preview = image.preview,
            imageCategory = type
        )
    }
}

fun ResponseSimilarFilmsByFilmId.convertToDbSimilar(filmId: Int): List<NewFilmSimilar>? {
    return this.films?.map { similar ->
        NewFilmSimilar(
            filmId = filmId,
            similarId = similar.filmId,
            name = similar.nameRu,
            poster = similar.posterUrl,
            posterPreview = similar.posterUrlPreview
        )
    }
}