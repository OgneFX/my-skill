package com.example.finalskillcinema.ui.adapters.holders

import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.finalskillcinema.R
import com.example.finalskillcinema.databinding.ItemSeriesEpisodeBinding
import com.example.finalskillcinema.ui.adapters.MyAdapterTypes

class ItemSeasonViewHolder(
    private val binding: ItemSeriesEpisodeBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var isClicked = false

    fun bindItem(item: MyAdapterTypes.ItemEpisode) {
        val text = binding.root.resources.getString(
            R.string.season_episode_name, item.season.episodeNumber, item.season.name
        )
        binding.episodeNumberName.text = text
        binding.episodeDate.text = formatReleaseDate(item.season.date)
        if (item.season.synopsis != null) {
            binding.episodeDescription.text = item.season.synopsis
            binding.episodeBtn.setOnClickListener {
                binding.episodeDescription.isVisible = !isClicked
                isClicked = !isClicked
                binding.episodeBtn.setImageResource(
                    if (isClicked) R.drawable.arrow_up else R.drawable.arrow_down
                )
            }
        }
    }

    private fun formatReleaseDate(date: String?): String {

        if (date != null) {
            val temp1 = date.split("-").reversed().toMutableList()
            temp1[1] = when (temp1[1]) {
                "01" -> "января"
                "02" -> "февраля"
                "03" -> "марта"
                "04" -> "апреля"
                "05" -> "мая"
                "06" -> "июня"
                "07" -> "июля"
                "08" -> "августа"
                "09" -> "сентября"
                "10" -> "октября"
                "11" -> "ноября"
                "12" -> "декабря"
                else -> ""
            }
            return temp1.joinToString(" ")
        } else return ""
    }
}