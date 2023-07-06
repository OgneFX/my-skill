package com.example.finalskillcinema.ui.search.filmadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import com.example.finalskillcinema.app.loadImage
import com.example.finalskillcinema.databinding.ItemFilmBinding
import com.example.finalskillcinema.entity.MenuItem


class SearchFilmAdapter(
    private val onClick: (Int) -> Unit
) : PagingDataAdapter<MenuItem, AllFilmViewHolder>(AllFilmsDiffUtil()) {
    override fun onBindViewHolder(holder: AllFilmViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            item?.let {
                holder.binding.apply {
                    itemFilmPoster.loadImage(item.posterUrlPreview)
                    itemFilmName.text = item.nameRu
                    itemFilmGenre.text = item.genres.joinToString(", ") { it.genre }
                    itemFilmRating.isVisible = false
                }
            }
        }
        holder.binding.itemFilmPoster.setOnClickListener { onClick(item!!.filmId) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllFilmViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllFilmViewHolder(binding)
    }
}