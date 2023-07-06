package com.example.finalskillcinema.ui.innermenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.finalskillcinema.R
import com.example.finalskillcinema.data.FilmCategories
import com.example.finalskillcinema.databinding.MenuInnerContainerBinding
import com.example.finalskillcinema.ui.adapters.CategoryAdapter
import com.example.finalskillcinema.ui.allfilms.AllFilmsFragment
import com.example.finalskillcinema.ui.states.StateInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MenuInner : Fragment() {
    private var _binding: MenuInnerContainerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MenuInnerViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MenuInnerContainerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stateLoadingListener()
        getCategories()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getCategories() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.MenuInnerList.collect {
                    categoryAdapter =
                        CategoryAdapter(20, it, { onClickShoAllButton(it) }, { onClickFilm(it) })
                    binding.categoryList.adapter = categoryAdapter
                }
            }
        }
    }

    private fun onClickFilm(filmId: Int) {
        val action = MenuInnerDirections.actionFragmentMenuToFragmentFilmDetail(filmId)
        findNavController().navigate(action)
    }

    private fun onClickShoAllButton(category: FilmCategories) {
        findNavController().navigate(
            R.id.action_fragmentMenu_to_fragmentAllFilms,
            bundleOf(AllFilmsFragment.KEY_FILM_CATEGORY to category)
        )
    }

    private fun stateLoadingListener() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadCategoryState.collect { state ->
                    when (state) {
                        is StateInfo.Loading -> {
                            binding.progressGroup.isVisible = true
                            binding.loadingProgress.isVisible = true
                            binding.loadingRefreshBtn.isVisible = false
                            binding.categoryList.isVisible = false
                        }
                        is StateInfo.Success -> {
                            binding.progressGroup.isVisible = false
                            binding.categoryList.isVisible = true
                        }
                        else -> {
                            binding.progressGroup.isVisible = true
                            binding.loadingProgress.isVisible = false
                            binding.loadingRefreshBtn.isVisible = true
                            binding.categoryList.isVisible = false
                            binding.loadingRefreshBtn.setOnClickListener { viewModel.getFilmsByCategories() }
                        }
                    }
                }
            }
        }
    }


}