package Presentation

import Domain.GetUsefulActivityUseCase
import Data.Helpful
import Entity.UsefulActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val GetUsefulActivityUseCase: GetUsefulActivityUseCase) : ViewModel() {

    private var x:UsefulActivity = Helpful()
    private var _state = MutableStateFlow(x)
    val state = _state.asStateFlow()

//    val useful: MutableLiveData<List<UsefulActivity>> = MutableLiveData<List<UsefulActivity>>()
//
//
//    fun getWords() {
//        viewModelScope.launch {
//            useful.postValue(useful.)
//        }
//    }

   suspend fun reloadUsefulActivity() {
        viewModelScope.launch {
            x = GetUsefulActivityUseCase.execut()
            _state.value = x
        }
    }


}