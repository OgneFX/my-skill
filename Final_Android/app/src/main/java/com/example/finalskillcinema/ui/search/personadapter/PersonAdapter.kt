package com.example.finalskillcinema.ui.search.personadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import com.example.finalskillcinema.api.old.personbyname.ItemPerson
import com.example.finalskillcinema.app.loadImage
import com.example.finalskillcinema.databinding.ItemFilmBinding


class PersonAdapter(
    private val onClick: (Int) -> Unit
) : PagingDataAdapter<ItemPerson, PersonViewHolder>(PersonDiffUtil()) {
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            item?.let {
                holder.binding.apply {
                    itemFilmPoster.loadImage(item.posterUrl)
                    itemFilmName.text = item.nameRu
                    itemFilmGenre.isVisible = false
                    itemFilmRating.isVisible = false
                }
            }
        }
        holder.binding.itemFilmPoster.setOnClickListener { onClick(item!!.kinopoiskId) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }
}