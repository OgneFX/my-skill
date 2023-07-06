package com.example.finalskillcinema.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalskillcinema.data.FilmCategories
import com.example.finalskillcinema.databinding.ItemCategoryListBinding
import com.example.finalskillcinema.ui.adapters.holders.CategoryViewHolder
import com.example.finalskillcinema.ui.innermenu.MenuInnerViewModel

class CategoryAdapter(
    private val maxListSize: Int,
    private val category: List<MenuInnerViewModel.Companion.MenuInnerList>,
    private val clickNextButton: (category: FilmCategories) -> Unit,
    private val clickFilms: (filmId: Int) -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryViewHolder(
        ItemCategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindItem(
            maxListSize,
            category[position],
            { clickNextButton(it) },
            { clickFilms(it) }
        )
    }

    override fun getItemCount() = category.size
}