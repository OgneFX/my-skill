package com.example.finalskillcinema.db.model

import androidx.room.Embedded
import androidx.room.Relation

data class FilmWithGenres(
    @Embedded
    val film: FilmsShortInfo,
    @Relation(
        entity = FilmGenres::class,
        parentColumn = "id",
        entityColumn = "film_id"
    )
    val genres: List<FilmGenres>,
    @Relation(
        entity = FilmMarkers::class,
        parentColumn = "id",
        entityColumn = "id"
    )
    val markers: FilmMarkers?
)

data class FilmWithDetailInfo(
    @Embedded
    val film: FilmsShortInfo,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val detailInfo: FilmDetailInfo?,
    @Relation(
        entity = FilmGenres::class,
        parentColumn = "id",
        entityColumn = "film_id"
    )
    val genres: List<FilmGenres>,
    @Relation(
        entity = FilmCountries::class,
        parentColumn = "id",
        entityColumn = "film_id"
    )
    val countries: List<FilmCountries>?,
    @Relation(
        entity = FilmPersons::class,
        parentColumn = "id",
        entityColumn = "film_id"
    )
    val persons: List<FilmPersons>?,
    @Relation(
        entity = FilmSimilar::class,
        parentColumn = "id",
        entityColumn = "film_id"
    )
    val similar: List<FilmSimilar>?,
    @Relation(
        entity = FilmImage::class,
        parentColumn = "id",
        entityColumn = "film_id"
    )
    val gallery: List<FilmImage>,
    @Relation(
        parentColumn = "id",
        entityColumn = "film_id"
    )
    val seriesEpisodes: List<SeasonEpisode>?
)

data class FilmWithGenreInHistory(
    @Embedded
    val inCache: HistoryFilms,
    @Relation(
        entity = FilmsShortInfo::class,
        parentColumn = "id",
        entityColumn = "id"
    )
    val films: FilmsShortInfo,
    @Relation(
        entity = FilmGenres::class,
        parentColumn = "id",
        entityColumn = "film_id"
    )
    val genres: FilmGenres
)