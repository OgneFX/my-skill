package com.example.finalskillcinema.ui.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.finalskillcinema.ui.adapters.MyAdapterTypes

class MyDiffUtil : DiffUtil.ItemCallback<MyAdapterTypes>() {
    override fun areItemsTheSame(oldItem: MyAdapterTypes, newItem: MyAdapterTypes) =
        oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: MyAdapterTypes, newItem: MyAdapterTypes) =
        oldItem == newItem
}