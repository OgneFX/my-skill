package com.example.finalskillcinema.ui.intro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalskillcinema.data.IntroResources
import com.example.finalskillcinema.databinding.IntroItemBinding

class IntroPageAdapter( private val introList: List<IntroResources>):
    RecyclerView.Adapter<IntroViewHolder>() {

        override fun getItemCount() = introList.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IntroViewHolder(
            IntroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

        override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
            holder.binding.apply {
                introImage.setImageResource(introList[position].imageId)
                introMessage.text = introList[position].message
            }
        }


    }
