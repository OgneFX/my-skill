package com.example.finalskillcinema.ui.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.finalskillcinema.app.loadImage
import com.example.finalskillcinema.databinding.ItemGalleryFilmDetailBinding
import com.example.finalskillcinema.ui.adapters.MyAdapterTypes

class ItemImageForFilmDetailViewHolder(
    private val binding: ItemGalleryFilmDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: MyAdapterTypes.ItemFilmImage) {
        binding.galleryImageFilmDetail.loadImage(item.image.preview)
    }
}