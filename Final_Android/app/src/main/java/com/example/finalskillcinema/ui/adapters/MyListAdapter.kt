package com.example.finalskillcinema.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalskillcinema.R
import com.example.finalskillcinema.databinding.*
import com.example.finalskillcinema.ui.adapters.holders.ItemFilmPersonViewHolder
import com.example.finalskillcinema.ui.adapters.holders.ItemFilmShortInfoViewHolder
import com.example.finalskillcinema.ui.adapters.holders.ItemFilmViewHolder
import com.example.finalskillcinema.ui.adapters.holders.ItemGalleryViewHolder
import com.example.finalskillcinema.ui.adapters.holders.ItemImageForFilmDetailViewHolder
import com.example.finalskillcinema.ui.adapters.holders.ItemSeasonViewHolder
import com.example.finalskillcinema.ui.adapters.holders.ItemSimilarViewHolder
import com.example.finalskillcinema.ui.adapters.holders.ItemGalleryFullscreenViewHolder

class MyListAdapter(
    private val maxListSize: Int?,
    private val clickEndButton: () -> Unit,
    private val clickItem: (itemId: Int) -> Unit
) : ListAdapter<MyAdapterTypes, RecyclerView.ViewHolder>(MyDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_staff_detail_film -> {
                ItemFilmPersonViewHolder(
                    ItemStaffDetailFilmBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            R.layout.item_film -> {
                ItemFilmViewHolder(
                    ItemFilmBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false)
                )
            }

            R.layout.item_gallery_film_detail -> {
                ItemImageForFilmDetailViewHolder(
                    ItemGalleryFilmDetailBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            R.layout.item_film_genreless -> {
                ItemFilmShortInfoViewHolder(
                    ItemFilmGenrelessBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            R.layout.item_similar -> {
                ItemSimilarViewHolder(
                    ItemSimilarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

            R.layout.item_series_episode -> {
                ItemSeasonViewHolder(
                    ItemSeriesEpisodeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            R.layout.item_gallery_image -> {
                ItemGalleryViewHolder(
                    ItemGalleryImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            R.layout.item_gallery_fullscreen -> {
                ItemGalleryFullscreenViewHolder(
                    ItemGalleryFullscreenBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> throw Exception("Unknown ViewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.item_staff_detail_film -> { (holder as ItemFilmPersonViewHolder)
                    .bindItem(getItem(position) as MyAdapterTypes.ItemFilmPerson) { clickItem(it) }
            }

            R.layout.item_film -> {
                if (maxListSize != null) {
                    val tempSize = if (itemCount <= maxListSize) itemCount else maxListSize + 1
                    if (position < tempSize - 1) {
                        (holder as ItemFilmViewHolder)
                            .bindItem(getItem(position) as MyAdapterTypes.ItemFilmWithGenre) { clickItem(it) }
                    } else (holder as ItemFilmViewHolder).bindNextShow(clickEndButton)
                } else (holder as ItemFilmViewHolder)
                    .bindItem(getItem(position) as MyAdapterTypes.ItemFilmWithGenre) { clickItem(it) }
            }

            R.layout.item_film_genreless -> {
                if (maxListSize != null) {
                    val tempSize = if (itemCount <= maxListSize) itemCount else maxListSize + 1
                    if (position < tempSize - 1) {
                        (holder as ItemFilmShortInfoViewHolder)
                            .bindItem(getItem(position) as MyAdapterTypes.ItemFilmShortInfo) { clickItem(it) }
                    } else if (position == tempSize - 1) {
                        (holder as ItemFilmShortInfoViewHolder).bindNextShow(clickEndButton)
                    }
                } else (holder as ItemFilmShortInfoViewHolder)
                    .bindItem(getItem(position) as MyAdapterTypes.ItemFilmShortInfo) { clickItem(it) }
            }

            R.layout.item_gallery_film_detail -> { (holder as ItemImageForFilmDetailViewHolder)
                    .bindItem(getItem(position) as MyAdapterTypes.ItemFilmImage)
            }

            R.layout.item_similar -> {
                if (maxListSize != null) {
                    val tempSize = if (itemCount <= maxListSize) itemCount else maxListSize + 1
                    if (position < tempSize - 1) {
                        (holder as ItemSimilarViewHolder)
                            .bindItem(getItem(position) as MyAdapterTypes.ItemFilmSimilar) {
                                clickItem(it)
                            }
                    } else (holder as ItemSimilarViewHolder).bindNextShow(clickEndButton)
                } else (holder as ItemSimilarViewHolder)
                    .bindItem(getItem(position) as MyAdapterTypes.ItemFilmSimilar) { clickItem(it) }
            }

            R.layout.item_series_episode -> { (holder as ItemSeasonViewHolder)
                    .bindItem(getItem(position) as MyAdapterTypes.ItemEpisode)
            }

            R.layout.item_gallery_image -> { (holder as ItemGalleryViewHolder)
                    .bindItem(getItem(position) as MyAdapterTypes.ItemGalleryImage) { clickItem(position) }
            }

            R.layout.item_gallery_fullscreen -> { (holder as ItemGalleryFullscreenViewHolder)
                    .bindItem(getItem(position) as MyAdapterTypes.ItemGalleryFullScreen)
            }

            else -> throw Exception("Unknown ViewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MyAdapterTypes.ItemFilmPerson -> R.layout.item_staff_detail_film
            is MyAdapterTypes.ItemFilmWithGenre -> R.layout.item_film
            is MyAdapterTypes.ItemFilmImage -> R.layout.item_gallery_film_detail
            is MyAdapterTypes.ItemFilmSimilar -> R.layout.item_similar
            is MyAdapterTypes.ItemFilmShortInfo -> R.layout.item_film_genreless
            is MyAdapterTypes.ItemEpisode -> R.layout.item_series_episode
            is MyAdapterTypes.ItemGalleryImage -> R.layout.item_gallery_image
            is MyAdapterTypes.ItemGalleryFullScreen -> R.layout.item_gallery_fullscreen
            else -> throw Exception("Unknown ViewType")
        }
    }
}