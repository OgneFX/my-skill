package com.example.finalskillcinema.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class FilmCategories(val text: String) : Parcelable {
    BEST("ТОП-250"),
    POPULAR("Популярное"),
    PREMIERS("Премьеры"),
    AWAIT("Самые ожиаемые"),
    TV_SERIES("Сериалы")
}