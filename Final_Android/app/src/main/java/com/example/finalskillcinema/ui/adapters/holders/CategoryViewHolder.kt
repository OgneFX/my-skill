package com.example.finalskillcinema.ui.adapters.holders

import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.example.finalskillcinema.data.FilmCategories
import com.example.finalskillcinema.databinding.ItemCategoryListBinding
import com.example.finalskillcinema.ui.adapters.MyAdapterTypes
import com.example.finalskillcinema.ui.adapters.MyListAdapter
import com.example.finalskillcinema.ui.innermenu.MenuInnerViewModel


class CategoryViewHolder(
    private val binding: ItemCategoryListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(
        maxListSize: Int,
        item: MenuInnerViewModel.Companion.MenuInnerList,
        clickNextButton: (category: FilmCategories) -> Unit,
        clickFilms: (filmId: Int) -> Unit
    ) {
        val adapter =
            MyListAdapter(
                maxListSize,
                { clickNextButton(item.category) },
                { clickFilms(it) })
        adapter.submitList(item.filmList.map { MyAdapterTypes.ItemFilmWithGenre(it) })
        binding.titleCategory.text = item.category.text
        binding.filmList.adapter = adapter
        binding.tvTitleShowAll.apply {
            this.isInvisible = item.filmList.size < maxListSize
            this.setOnClickListener { clickNextButton(item.category) }
        }
    }
}