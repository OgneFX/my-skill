package com.example.finalskillcinema.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalskillcinema.data.GALLERY_TYPES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.finalskillcinema.db.model.FilmImage
import com.example.finalskillcinema.domain.GetGalleryByIdUseCase
import javax.inject.Inject

@HiltViewModel
class ViewModelGallery @Inject constructor(
    private val getGalleryByIdUseCase: GetGalleryByIdUseCase
) : ViewModel() {
    private lateinit var allImagesByFilm: List<FilmImage>
    private lateinit var currentCategory: String

    private val _images = MutableStateFlow<List<FilmImage>>(emptyList())
    val images = _images.asStateFlow()

    private val _galleryChipList = MutableStateFlow<Map<String, Int>>(emptyMap())
    val galleryChipList = _galleryChipList.asStateFlow()

    fun setGallery(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getGalleryByIdUseCase.executeGalleryByFilmId(filmId)
            allImagesByFilm = response
            val tempChipList = mutableMapOf<String, Int>()
            GALLERY_TYPES.forEach {
                tempChipList[it.key] = response.count { image -> image.imageCategory == it.key }
            }
            _galleryChipList.value = tempChipList
            _images.value = response
        }
    }

    fun updateGalleryType(galleryType: String) {
        currentCategory = galleryType
        viewModelScope.launch(Dispatchers.IO) {
            val tempList: List<FilmImage> = allImagesByFilm
                .filter { it.imageCategory == galleryType }
            _images.value = tempList
        }
    }

    fun getCurrentCategory() = currentCategory

    fun clearChipMap() {
        _galleryChipList.value = emptyMap()
    }
}