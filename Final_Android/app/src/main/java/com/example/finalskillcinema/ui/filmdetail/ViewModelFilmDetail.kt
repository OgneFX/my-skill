package com.example.finalskillcinema.ui.filmdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.example.finalskillcinema.data.ParamsFilterGallery
import com.example.finalskillcinema.db.model.FilmMarkers
import com.example.finalskillcinema.db.model.FilmWithDetailInfo
import com.example.finalskillcinema.domain.GetFilmByIdUseCase
import com.example.finalskillcinema.domain.GetFilmMarkersUseCase
import com.example.finalskillcinema.domain.GetFilmsHistoryUseCase
import com.example.finalskillcinema.ui.states.StateInfo
import javax.inject.Inject

@HiltViewModel
class ViewModelFilmDetail @Inject constructor(
    private val getFilmByIdUseCase: GetFilmByIdUseCase,
    private val getFilmsHistoryUseCase: GetFilmsHistoryUseCase,
    private val getFilmMarkersUseCase: GetFilmMarkersUseCase
) : ViewModel() {

    private var currentFilmId: Int = 0

    private val _loadCurrentFilmState = MutableStateFlow<StateInfo>(StateInfo.Default)
    val loadCurrentFilmState = _loadCurrentFilmState.asStateFlow()

    private val _filmDetailInfo = MutableStateFlow<FilmWithDetailInfo?>(null)
    val filmDetailInfo = _filmDetailInfo.asStateFlow()

    fun getFilmById(filmId: Int) {
        currentFilmId = filmId

        updateParamsFilterGallery()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loadCurrentFilmState.value = StateInfo.Loading
                getFilmsHistoryUseCase.addFilmToHistory(filmId)
                getFilmMarkersUseCase.addMarkers(filmId)
                val tempFilm: FilmWithDetailInfo = getFilmByIdUseCase.executeFilmDetailInfoById(filmId)

                Log.d(
                    TAG,
                    "getFilmById: filmId = $filmId, film = ${tempFilm.film.filmId}, ${tempFilm.film.name}"
                )
                _filmDetailInfo.value = tempFilm
                _loadCurrentFilmState.value = StateInfo.Success
            } catch (e: Throwable) {
                _loadCurrentFilmState.value = StateInfo.Error(e.message.toString())
            }
        }
    }

    private fun updateParamsFilterGallery(
        filmId: Int = currentFilmId,
        galleryType: String = "STILL"
    ) {
        currentParamsFilterGallery =
            currentParamsFilterGallery.copy(filmId = filmId, galleryType = galleryType)
    }

    // work with database
    fun checkFilmInDB(filmId: Int): Flow<FilmMarkers?> {
        return getFilmMarkersUseCase.executeMarkersByFilm(filmId)
    }

    fun updateFilmMarkers(filmId: Int, isFavorite: Int, inCollection: Int, isViewed: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getFilmMarkersUseCase.updateMarkers(filmId, isFavorite, inCollection, isViewed)
        }
    }

    companion object {
        private const val TAG = "ViewModelFilmDetail"

        private var currentParamsFilterGallery = ParamsFilterGallery(
            filmId = 328,
            galleryType = "STILL"
        )
    }
}