package com.example.finalskillcinema.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalskillcinema.R
import com.example.finalskillcinema.databinding.ItemFilmBinding
import com.example.finalskillcinema.databinding.ItemProfileCollectionBinding


class ProfileAdapter(
    private val maxListSize: Int,
    private val clickEndButton: () -> Unit,
    private val clickItem: (filmId: Int) -> Unit
) : ListAdapter<ProfileAdapterTypes, RecyclerView.ViewHolder>(ProfileDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_film -> {
                ProfileDBViewHolder(
                    ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> {
                ProfileCollectionViewHolder(
                    ItemProfileCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.item_film -> {
                val tempSize = if (itemCount <= maxListSize) itemCount else maxListSize + 1
                if (position < tempSize - 1) {
                    (holder as ProfileDBViewHolder)
                        .bindItem(getItem(position) as ProfileAdapterTypes.ItemDB) { clickItem(it) }
                } else (holder as ProfileDBViewHolder)
                    .bindNextShow(clickEndButton)
            }
            R.layout.item_profile_collection -> {
                (holder as ProfileCollectionViewHolder)
                    .bindItem(getItem(position) as ProfileAdapterTypes.ItemCollection)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ProfileAdapterTypes.ItemDB -> R.layout.item_film
            else -> R.layout.item_profile_collection
        }
    }
}