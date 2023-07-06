package com.example.finalskillcinema.ui.allfilms

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finalskillcinema.data.FilmCategories
import com.example.finalskillcinema.databinding.AllFilmsFragmentBinding
import com.example.finalskillcinema.ui.adapters.MyAdapterTypes
import com.example.finalskillcinema.ui.adapters.MyPagingAdapter
import kotlinx.coroutines.launch


class AllFilmsFragment : Fragment() {
    private var _binding: AllFilmsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelAllFilms by viewModels()
    private lateinit var categoryAdapter: MyPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = AllFilmsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val category: FilmCategories? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requireArguments().getParcelable(KEY_FILM_CATEGORY, FilmCategories::class.java)
            } else {
                requireArguments().getParcelable(KEY_FILM_CATEGORY)
            }

        if (category != null) {
            viewModel.setCurrentCategory(category)
            binding.allFilmsCategoryTv.text = category.text
        }

        setAdapter()                            // set adapter
        setFilmList()                           // set film list

        binding.allFilmsToHomeBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.loadingRefreshBtn.setOnClickListener { categoryAdapter.retry() }
    }

    private fun setAdapter() {
        categoryAdapter = MyPagingAdapter { onClickFilm(it) }

        binding.allFilmsList.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.allFilmsList.adapter = categoryAdapter

        categoryAdapter.addLoadStateListener { state ->
            val currentState = state.refresh
            binding.allFilmsList.isVisible = currentState != LoadState.Loading
            binding.progressGroup.isVisible = currentState == LoadState.Loading
            binding.loadingRefreshBtn.isVisible = currentState != LoadState.Loading

            when (currentState) {
                is LoadState.Loading -> {
                    binding.allFilmsList.isVisible = false
                    binding.progressGroup.isVisible = true
                    binding.loadingRefreshBtn.isVisible = false
                }

                is LoadState.NotLoading -> {
                    binding.allFilmsList.isVisible = true
                    binding.progressGroup.isVisible = false
                    binding.loadingRefreshBtn.isVisible = true
                }

                else -> {
                    binding.allFilmsList.isVisible = false
                    binding.loadingProgress.isVisible = false
                    binding.loadingRefreshBtn.isVisible = true
                }
            }
        }
    }

    private fun setFilmList() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allFilmsByCategory.collect {

                    categoryAdapter.submitData(it.map { film -> MyAdapterTypes.ItemFilmWithGenre(film) })
                }
            }
        }
    }

    private fun onClickFilm(filmId: Int) {
        val action = AllFilmsFragmentDirections.actionFragmentAllFilmsToFragmentFilmDetail(filmId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_FILM_CATEGORY = "KEY_FILM_CATEGORY"
    }
}