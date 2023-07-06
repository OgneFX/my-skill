package com.first.acttwelve.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow<State>(State.NotReady)
    val state = _state.asStateFlow()

    fun searchButton(num: Int) {

        viewModelScope.launch {
            println("Out")
            if (num >= 3) {
                println("In 1")
                _state.value = State.Ready
                println("In 2")
            } else _state.value = State.NotReady


        }


    }

    fun searchButtonActive() {
        viewModelScope.launch {
            _state.value = State.Loading
            delay(5000)
            _state.value = State.Ready
        }
    }
}




