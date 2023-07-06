package com.example.finalskillcinema.ui.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalskillcinema.db.model.SeasonEpisode
import com.example.finalskillcinema.domain.GetSeasonsUseCase
import com.example.finalskillcinema.ui.states.StateInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSeasons @Inject constructor(
    private val getSeasonsUseCase: GetSeasonsUseCase
) : ViewModel() {
    private val _seasons = MutableStateFlow<List<SeasonEpisode>>(emptyList())
    val seasons = _seasons.asStateFlow()

    private val _loadCategoryState = MutableStateFlow<StateInfo>(StateInfo.Default)
    val loadCategoryState = _loadCategoryState.asStateFlow()

    fun getSeasons(seriesId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _loadCategoryState.value = StateInfo.Loading
            val temp: List<SeasonEpisode> = getSeasonsUseCase.executeSeasons(seriesId)
            _seasons.value = temp
            _loadCategoryState.value = StateInfo.Success
        }
    }
}