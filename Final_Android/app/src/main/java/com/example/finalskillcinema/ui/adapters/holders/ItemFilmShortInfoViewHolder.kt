package com.example.finalskillcinema.ui.adapters.holders

import android.util.Log
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.example.finalskillcinema.app.loadImage
import com.example.finalskillcinema.databinding.ItemFilmGenrelessBinding

import com.example.finalskillcinema.ui.adapters.MyAdapterTypes

class ItemFilmShortInfoViewHolder(private val binding: ItemFilmGenrelessBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindNextShow(clickNextButton: () -> Unit) {
        binding.apply {
            showAll.isInvisible = false
            itemFilmWithoutGenres.isInvisible = true
        }

        binding.btnArrowShowAll.setOnClickListener { clickNextButton() }
    }

    fun bindItem(item: MyAdapterTypes.ItemFilmShortInfo, clickFilms: (filmId: Int) -> Unit) {
        Log.d("ItemShortInfoVH", "bindItem: ${item.filmShortInfo.poster}")
        binding.apply {
            showAll.isInvisible = true
            itemFilmWithoutGenres.isInvisible = false
            itemFilmWithoutGenresName.text = item.filmShortInfo.name
            itemFilmWithoutGenresPoster.loadImage(item.filmShortInfo.poster)
            if (item.filmShortInfo.rating != null) {
                itemFilmWithoutGenresRating.text = item.filmShortInfo.rating
            } else {
                itemFilmWithoutGenresRating.isInvisible = false
            }
        }
        binding.itemFilmWithoutGenres.setOnClickListener { clickFilms(item.filmShortInfo.filmId) }
    }
}