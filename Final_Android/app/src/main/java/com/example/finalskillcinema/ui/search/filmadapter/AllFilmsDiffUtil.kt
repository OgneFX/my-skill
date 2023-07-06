package com.example.finalskillcinema.ui.search.filmadapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.finalskillcinema.entity.MenuItem

class AllFilmsDiffUtil : DiffUtil.ItemCallback<MenuItem>() {
    override fun areItemsTheSame(
        oldItem: MenuItem,
        newItem: MenuItem
    ) = oldItem.filmId == newItem.filmId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: MenuItem,
        newItem: MenuItem
    ) = oldItem == newItem
}