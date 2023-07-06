package com.example.finalskillcinema.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalskillcinema.R
import com.example.finalskillcinema.databinding.ItemFilmBinding
import com.example.finalskillcinema.ui.adapters.holders.ItemFilmViewHolder

class MyPagingAdapter(
    private val onClick: (Int) -> Unit
) : PagingDataAdapter<MyAdapterTypes, RecyclerView.ViewHolder>(MyDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_film -> {
                ItemFilmViewHolder(
                    ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> throw Exception("Unknown ViewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {when (holder.itemViewType) {
            R.layout.item_film -> {
                val item = getItem(position) as MyAdapterTypes.ItemFilmWithGenre
                (holder as ItemFilmViewHolder).bindItem(item) { onClick(it) }
            }
            else -> throw Exception("Unknown ViewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MyAdapterTypes.ItemFilmWithGenre -> R.layout.item_film
            else -> throw Exception("Unknown ViewType")
        }
    }
}