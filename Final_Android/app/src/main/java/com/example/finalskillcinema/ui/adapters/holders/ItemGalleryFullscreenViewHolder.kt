package com.example.finalskillcinema.ui.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.finalskillcinema.app.loadImage
import com.example.finalskillcinema.databinding.ItemGalleryFullscreenBinding
import com.example.finalskillcinema.ui.adapters.MyAdapterTypes

class ItemGalleryFullscreenViewHolder(val binding: ItemGalleryFullscreenBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bindItem(image: MyAdapterTypes.ItemGalleryFullScreen) {
            binding.galleryImageFullscreen.loadImage(image.image.image)
        }
}