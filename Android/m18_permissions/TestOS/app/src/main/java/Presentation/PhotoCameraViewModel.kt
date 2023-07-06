package Presentation

import Data.LocalRepository
import Entity.Photo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoCameraViewModel(val getCamera: LocalRepository): ViewModel() {

    fun postPhoto(photo: Photo) {
        viewModelScope.launch(Dispatchers.IO) {
            getCamera.addPhoto(photo)
        }
    }

}