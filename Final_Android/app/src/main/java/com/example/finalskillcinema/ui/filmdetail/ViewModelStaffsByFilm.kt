package com.example.finalskillcinema.ui.filmdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.finalskillcinema.db.model.FilmPersons
import com.example.finalskillcinema.domain.GetActorsListUseCase
import com.example.finalskillcinema.ui.states.StateInfo
import javax.inject.Inject

@HiltViewModel
class ViewModelStaffsByFilm @Inject constructor(
    private val getActorsByFilmIdUseCase: GetActorsListUseCase
) : ViewModel() {

    private val _loadCategoryState = MutableStateFlow<StateInfo>(StateInfo.Default)
    val loadCategoryState = _loadCategoryState.asStateFlow()

    private val _persons = MutableStateFlow<List<FilmPersons>>(emptyList())
    val persons = _persons.asStateFlow()

    fun getStaffsByFilm(filmId: Int, professionKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loadCategoryState.value = StateInfo.Loading
            val persons = getActorsByFilmIdUseCase.executePersonsList(filmId)
            _persons.value = if (professionKey == "ACTOR") {
                persons.filter { it.professionKey == professionKey }
            } else {
                persons.filter { it.professionKey != professionKey }
            }
            _loadCategoryState.value = StateInfo.Success
        }
    }
}