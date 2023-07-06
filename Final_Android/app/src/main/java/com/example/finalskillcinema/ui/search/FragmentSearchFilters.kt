package com.example.finalskillcinema.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalskillcinema.R
import kotlinx.coroutines.launch
import com.example.finalskillcinema.api.old.filmbyfilter.FilterCountry
import com.example.finalskillcinema.databinding.FragmentSearchFiltersBinding
import com.example.finalskillcinema.entity.FilterCountryGenre
import com.example.finalskillcinema.ui.search.filteradapter.SearchFiltersAdapter



class FragmentSearchFilters : Fragment() {
    private var _binding: FragmentSearchFiltersBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SearchFiltersAdapter

    private val viewModel: ViewModelSearch by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchFiltersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: FragmentSearchFiltersArgs by navArgs()

        setAdapter()                                // Установка адаптера списка
        getFilterList(args.filterType)              // Получение списка по указанному типу
        setSearchVewListener(args.filterType)       // Установка листенера строки поиска

        binding.searchFiltersBackBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setAdapter() {
        val divider = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val dividerResource =
            ResourcesCompat.getDrawable(resources, R.drawable.divider_recyclerview, null)
        divider.setDrawable(dividerResource!!)

        adapter = SearchFiltersAdapter { onItemClick(it) }
        binding.searchFiltersList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.searchFiltersList.adapter = adapter
        binding.searchFiltersList.addItemDecoration(divider)
    }

    private fun getFilterList(filterType: String) {
        viewModel.setFilterValues(filterType)
        binding.searchFiltersSv.hint = when (filterType) {
            KEY_COUNTRY -> getString(R.string.search_filters_country_choose)
            KEY_GENRE -> getString(R.string.search_filters_genre_choose)
            else -> ""
        }
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filterValuesCountriesGenres.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun setSearchVewListener(filterType: String) {
        binding.searchFiltersSv.doOnTextChanged { text, _, _, _ ->
            viewModel.updateFilterCountriesGenres(filterType, text.toString())
        }
    }

    private fun onItemClick(filterType: FilterCountryGenre) {
        val newFilterValue = when (filterType) {
            is FilterCountry -> {
                viewModel.getFiltersFull().copy(countries = mapOf(filterType.id to filterType.name))
            }
            else -> {
                viewModel.getFiltersFull().copy(genres = mapOf(filterType.id to filterType.name))
            }
        }
        viewModel.updateFiltersFull(newFilterValue)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_COUNTRY = "country"
        const val KEY_GENRE = "genre"
    }
}