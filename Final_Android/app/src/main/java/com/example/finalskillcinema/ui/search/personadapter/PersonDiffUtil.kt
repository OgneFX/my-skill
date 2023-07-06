package com.example.finalskillcinema.ui.search.personadapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.finalskillcinema.api.old.personbyname.ItemPerson

class PersonDiffUtil : DiffUtil.ItemCallback<ItemPerson>() {
    override fun areItemsTheSame(
        oldItem: ItemPerson,
        newItem: ItemPerson
    ) = oldItem.kinopoiskId == newItem.kinopoiskId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: ItemPerson,
        newItem: ItemPerson
    ) = oldItem == newItem
}