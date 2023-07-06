package com.example.finalskillcinema.ui.persondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.finalskillcinema.db.model.FilmsShortInfo
import com.example.finalskillcinema.db.model.PersonWithDetailInfo
import com.example.finalskillcinema.domain.GetPersonByIdUseCase
import com.example.finalskillcinema.ui.states.StateInfo
import javax.inject.Inject

@HiltViewModel
class ViewModelPersonDetail @Inject constructor(
    private val getStaffByIdUseCase: GetPersonByIdUseCase
) : ViewModel() {
    private val _loadCurrentStaff = MutableStateFlow<StateInfo>(StateInfo.Default)
    val loadCurrentStaff = _loadCurrentStaff.asStateFlow()

    private val _currentPerson = MutableStateFlow<PersonWithDetailInfo?>(null)
    val currentPerson = _currentPerson.asStateFlow()

    private val _films = MutableStateFlow<List<FilmsShortInfo>>(emptyList())
    val films = _films.asStateFlow()

    fun getPersonDetail(personId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loadCurrentStaff.value = StateInfo.Loading

                val temp = getStaffByIdUseCase.executePersonDetailInfo(personId)

                val films = temp.films
                val tempFilmList = mutableListOf<FilmsShortInfo>()
                films?.forEach {
                    tempFilmList.add(getStaffByIdUseCase.executeFilmShortInfo(it.filmId))
                }

                _films.value = tempFilmList
                _currentPerson.value = temp

                _loadCurrentStaff.value = StateInfo.Success
            } catch (e: Exception) {
                _loadCurrentStaff.value = StateInfo.Error(e.message.toString())
            }
        }
    }
}