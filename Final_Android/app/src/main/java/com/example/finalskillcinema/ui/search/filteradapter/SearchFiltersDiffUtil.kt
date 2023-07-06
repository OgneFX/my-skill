package com.example.finalskillcinema.ui.search.filteradapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.finalskillcinema.entity.FilterCountryGenre


class SearchFiltersDiffUtil : DiffUtil.ItemCallback<FilterCountryGenre>() {
    override fun areItemsTheSame(
        oldItem: FilterCountryGenre,
        newItem: FilterCountryGenre
    ) = oldItem.name == newItem.name

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: FilterCountryGenre,
        newItem: FilterCountryGenre
    ) = oldItem == newItem
}