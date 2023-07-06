package com.example.finalskillcinema.entity

import com.example.finalskillcinema.api.old.filmbyfilter.Genre

interface MenuItem {
    val filmId: Int
    val posterUrlPreview: String
    val nameRu: String?
    val rating: String?
    val genres: List<Genre>

}