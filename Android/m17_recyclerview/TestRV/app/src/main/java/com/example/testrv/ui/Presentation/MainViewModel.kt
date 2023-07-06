package com.example.testrv.ui.Presentation

import Data.Photos
import Domain.NasaPhotoUseCase
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel private constructor(private val repository: NasaPhotoUseCase) : ViewModel()  {
    constructor():this(NasaPhotoUseCase())

    private val _nasaData = MutableStateFlow<List<Photos>>(emptyList())
    val nasaData = _nasaData.asStateFlow()

    init {
        loadNasaData()
    }

    private fun loadNasaData() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.getNasaPhoto()
            }.fold(

                onSuccess = {_nasaData.value = it
                    println("we here 1")},
                onFailure = { Log.d("NASA ViewModel",it.message?:" HEY")}
            )
        }
    }


}