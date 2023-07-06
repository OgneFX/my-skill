package com.example.finalskillcinema.ui.allfilms

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.finalskillcinema.data.FilmCategories
import com.example.finalskillcinema.data.TOP_TYPES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import com.example.finalskillcinema.db.model.FilmWithGenres
import com.example.finalskillcinema.domain.GetTopFilmsUseCase
import javax.inject.Inject

@HiltViewModel
class ViewModelAllFilms @Inject constructor(
    private val getTopFilmsUseCase: GetTopFilmsUseCase
) : ViewModel() {

    lateinit var allFilmsByCategory: Flow<PagingData<FilmWithGenres>>

    fun setCurrentCategory(category: FilmCategories) {
        val categoryForRequest =
            if (category.name == FilmCategories.PREMIERS.name) category.name
            else TOP_TYPES.getValue(category)

        allFilmsByCategory = getTopFilmsUseCase
            .executeTopFilmsPaging(categoryName = categoryForRequest)
    }
}