package Presentation

import Data.LocalRepository
import Entity.Photo
import Entity.PhotoDao
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class PhotoGaleryViewModel(val  getPhoto : LocalRepository) : ViewModel() {

    val photoForGalery: MutableLiveData<List<Photo>> = MutableLiveData<List<Photo>>()
    init {

    }

    fun getPhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            photoForGalery.postValue(getPhoto.getPhoto())
        }
    }


}