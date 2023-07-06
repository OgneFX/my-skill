package com.example.finalskillcinema.ui.adapters

import com.example.finalskillcinema.db.model.FilmImage
import com.example.finalskillcinema.db.model.FilmPersons
import com.example.finalskillcinema.db.model.FilmSimilar
import com.example.finalskillcinema.db.model.FilmWithGenres
import com.example.finalskillcinema.db.model.FilmsShortInfo
import com.example.finalskillcinema.db.model.SeasonEpisode

sealed class MyAdapterTypes {
    data class ItemFilmWithGenre(val filmWithGenre: FilmWithGenres): MyAdapterTypes()
    data class ItemFilmShortInfo(val filmShortInfo: FilmsShortInfo): MyAdapterTypes()
    data class ItemFilmSimilar(val similar: FilmSimilar): MyAdapterTypes()
    data class ItemFilmPerson(val person: FilmPersons): MyAdapterTypes()
    data class ItemFilmImage(val image: FilmImage): MyAdapterTypes()
    data class ItemEpisode(val season: SeasonEpisode): MyAdapterTypes()
    data class ItemGalleryImage(val image: FilmImage): MyAdapterTypes()
    data class ItemGalleryFullScreen(val image: FilmImage): MyAdapterTypes()
}