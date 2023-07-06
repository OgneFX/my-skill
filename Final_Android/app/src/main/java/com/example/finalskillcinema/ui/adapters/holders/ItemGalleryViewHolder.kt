package com.example.finalskillcinema.ui.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.finalskillcinema.app.loadImage
import com.example.finalskillcinema.databinding.ItemGalleryImageBinding
import com.example.finalskillcinema.ui.adapters.MyAdapterTypes

class ItemGalleryViewHolder(val binding: ItemGalleryImageBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bindItem(image: MyAdapterTypes.ItemGalleryImage, onClick: () -> Unit) {
            binding.galleryImage.loadImage(image.image.image)
            binding.root.setOnClickListener { onClick() }
        }
}