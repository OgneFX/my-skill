package com.example.finalskillcinema.ui.innermenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalskillcinema.app.prepareToShow
import com.example.finalskillcinema.data.FilmCategories
import com.example.finalskillcinema.data.ParamsFilterFilm
import com.example.finalskillcinema.data.TOP_TYPES
import com.example.finalskillcinema.db.model.FilmWithGenres
import com.example.finalskillcinema.domain.GetTopFilmsUseCase
import com.example.finalskillcinema.ui.states.StateInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MenuInnerViewModel @Inject constructor(
    private val getTopFilmsUseCase: GetTopFilmsUseCase
) : ViewModel() {
    init {
        getFilmsByCategories()
    }

    private val _MenuInnerList = MutableStateFlow<List<MenuInnerList>>(emptyList())
    val MenuInnerList = _MenuInnerList.asStateFlow()

    private val _loadCategoryState = MutableStateFlow<StateInfo>(StateInfo.Default)
    val loadCategoryState = _loadCategoryState.asStateFlow()

    fun getFilmsByCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loadCategoryState.value = StateInfo.Loading
                val list = listOf(
                    MenuInnerList(
                        category = FilmCategories.BEST,
                        filmList = getTopFilmsUseCase.executeTopFilms(
                            topType = TOP_TYPES.getValue(FilmCategories.BEST),
                            page = 1
                        ).prepareToShow(20)
                    ),
                    MenuInnerList(
                        category = FilmCategories.PREMIERS,
                        filmList = getTopFilmsUseCase.executeTopFilms(
                            topType = FilmCategories.PREMIERS.name,
                            page = null
                        ).prepareToShow(20)
                    ),
                    MenuInnerList(
                        category = FilmCategories.AWAIT,
                        filmList = getTopFilmsUseCase.executeTopFilms(
                            topType = TOP_TYPES.getValue(FilmCategories.AWAIT),
                            page = 1
                        ).prepareToShow(20)
                    ),
                    MenuInnerList(
                        category = FilmCategories.POPULAR,
                        filmList = getTopFilmsUseCase.executeTopFilms(
                            topType = TOP_TYPES.getValue(FilmCategories.POPULAR),
                            page = 1
                        ).prepareToShow(20)
                    ),
                    MenuInnerList(
                        category = FilmCategories.TV_SERIES,
                        filmList = getTopFilmsUseCase.executeTopFilms(
                            topType = TOP_TYPES.getValue(FilmCategories.TV_SERIES),
                            filters = ParamsFilterFilm(
                                type = TOP_TYPES.getValue(FilmCategories.TV_SERIES),
                                ratingFrom = 6
                            ),
                            page = 1
                        ).prepareToShow(20)
                    )
                )
                _MenuInnerList.value = list
                _loadCategoryState.value = StateInfo.Success
            } catch (e: Throwable) {
                _loadCategoryState.value = StateInfo.Error(e.message.toString())
            }
        }
    }

    companion object {
        data class MenuInnerList(
            val category: FilmCategories,
            val filmList: List<FilmWithGenres>
        )
    }




}