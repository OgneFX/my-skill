package com.example.finalskillcinema.ui.profile.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.finalskillcinema.ui.profile.adapter.ProfileAdapterTypes

class ProfileDiff : DiffUtil.ItemCallback<ProfileAdapterTypes>() {
    override fun areItemsTheSame(oldItem: ProfileAdapterTypes, newItem: ProfileAdapterTypes) =
        oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: ProfileAdapterTypes,
        newItem: ProfileAdapterTypes
    ) = oldItem == newItem
}