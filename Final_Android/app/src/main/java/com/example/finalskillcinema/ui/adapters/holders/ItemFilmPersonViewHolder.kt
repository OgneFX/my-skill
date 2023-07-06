package com.example.finalskillcinema.ui.adapters.holders

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.finalskillcinema.app.loadImage
import com.example.finalskillcinema.databinding.ItemStaffDetailFilmBinding


import com.example.finalskillcinema.ui.adapters.MyAdapterTypes

class ItemFilmPersonViewHolder(
    private val binding: ItemStaffDetailFilmBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: MyAdapterTypes.ItemFilmPerson, clickActor: (actorId: Int) -> Unit) {
        binding.apply {
            actorAvatarFilmDetail.loadImage(item.person.poster)
            actorNameFilmDetail.text = item.person.name
            if (item.person.description != null) {
                actorRoleFilmDetail.text =  item.person.description
                actorRoleFilmDetail.isVisible = true
            } else {
                actorRoleFilmDetail.isVisible = false
            }
        }
        binding.root.setOnClickListener { clickActor(item.person.personId) }
    }
}