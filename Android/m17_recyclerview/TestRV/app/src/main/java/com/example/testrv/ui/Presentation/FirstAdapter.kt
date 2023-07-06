package com.example.testrv.ui.Presentation

import Data.Photos
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide


import com.example.testrv.databinding.NasaItemBinding

class FirstAdapter(private val onClick: (Photos)-> Unit) : RecyclerView.Adapter<FirstViewHolder>() {
    private var data: List<Photos> = emptyList()
    fun setData(data: List<Photos>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstViewHolder {
        val binding = NasaItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FirstViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FirstViewHolder, position: Int) {
        val item = data.getOrNull(position)
        with(holder.binding) {
            roverName.text = "Rover:" + item?.rover?.name.toString()
            cameraName.text = "Camera: " + item?.camera?.name.toString()
            sol.text = "Sol: " + item?.sol.toString()
            date.text = "Date: " + item?.earthDate.toString()
            println(item?.imgSrc)
            item?.let {
//                photo.load(it.imgSrc)

                Glide
                    .with(photo.context)
                    .load(it.imgSrc)
                    .into(photo)
            }
            holder.binding.root.setOnClickListener {
                item?.let{
                    onClick(item)
                }
            }
        }
    }


}


class FirstViewHolder(val binding: NasaItemBinding): RecyclerView.ViewHolder(binding.root)