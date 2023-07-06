package com.example.finalskillcinema.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.finalskillcinema.R
import com.example.finalskillcinema.data.FILM_TYPE
import com.example.finalskillcinema.data.SORTING_PARAMS
import com.example.finalskillcinema.databinding.FragmentSearchSettingsBinding
import com.google.android.material.slider.RangeSlider
import kotlinx.coroutines.launch


class FragmentSearchSettings : Fragment() {
    private var _binding: FragmentSearchSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelSearch by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchSettingsBackBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        setTextViews()                          // Установка значений в TextView
        setFilterFilmType()                     // Установка фильтра по типу фильма
        setRatingSlider()                       // Установка Slider рейтинга
        setFilterSorting()                      // Установка сортировки списка фильмов

        onClickFilterCountryGenreChoose()       // Установка перехода к выбору страны и жанра
        onClickYearPiker()                      // Установка перехода к выбору года
    }

    private fun setTextViews() {
        binding.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.filterFlow.collect { filter ->
                        val country = filter.countries
                        val genre = filter.genres
                        searchRadioGroupFilmType.check(
                            when (filter.type) {
                                FILM_TYPE[0] -> binding.searchRadioFilms.id
                                FILM_TYPE[2] -> binding.searchRadioSeries.id
                                else -> binding.searchRadioAll.id
                            }
                        )
                        searchSettingsCountryTv.text =
                            if (country.values.isNotEmpty()) country.values.first()
                            else getString(R.string.search_filters_countries_default)
                        searchSettingsGenreTv.text =
                            if (genre.values.isNotEmpty()) genre.values.first()
                            else getString(R.string.search_filters_genres_default)
                        searchSettingsYearTv.text =
                            getString(
                                R.string.search_settings_years_text,
                                filter.yearFrom, filter.yearTo
                            )
                        searchRadioGroupSorting.check(
                            when (filter.order) {
                                SORTING_PARAMS[2] -> binding.searchRadioSortingDate.id
                                SORTING_PARAMS[1] -> binding.searchRadioSortingPopular.id
                                else -> binding.searchRadioSortingRating.id
                            }
                        )
                    }
                }
            }
        }
    }

    private fun setFilterFilmType() {
        binding.searchRadioGroupFilmType.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.searchRadioFilms.id -> {
                    viewModel.updateFiltersFull(
                        viewModel.getFiltersFull().copy(type = FILM_TYPE[0]))
                }
                binding.searchRadioAll.id -> {
                    viewModel.updateFiltersFull(
                        viewModel.getFiltersFull().copy(type = FILM_TYPE[4]))
                }
                else -> {
                    viewModel.updateFiltersFull(
                        viewModel.getFiltersFull().copy(type = FILM_TYPE[2]))
                }
            }
        }
    }

    private fun setRatingSlider() {
        binding.searchSettingsRangeStart.text =
            resources.getInteger(R.integer.settings_rating_slider_start).toString()
        binding.searchSettingsRangeEnd.text =
            resources.getInteger(R.integer.settings_rating_slider_end).toString()
        binding.searchSettingsRatingSlider.addOnSliderTouchListener(object :
            RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {
                if (slider.values == listOf(1f, 10f)) {
                    binding.searchSettingsRatingTv.text = "любой"
                } else {
                    val values = slider.values.map { it.toInt() }
                    binding.searchSettingsRatingTv.text =
                        resources.getString(
                            R.string.search_settings_rating_text,
                            values[0],
                            values[1]
                        )
                    binding.searchSettingsRangeStart.text = values[0].toString()
                    binding.searchSettingsRangeEnd.text = values[1].toString()
                    viewModel.updateFiltersFull(
                        viewModel.getFiltersFull().copy(
                            ratingFrom = values[0], ratingTo = values[1]
                        )
                    )
                }
            }

            override fun onStopTrackingTouch(slider: RangeSlider) {
                if (slider.values == listOf(1f, 10f)) {
                    binding.searchSettingsRatingTv.text = "любой"
                } else {
                    val values = slider.values.map { it.toInt() }
                    binding.searchSettingsRatingTv.text =
                        resources.getString(
                            R.string.search_settings_rating_text,
                            values[0],
                            values[1]
                        )
                    binding.searchSettingsRangeStart.text = values[0].toString()
                    binding.searchSettingsRangeEnd.text = values[1].toString()
                    viewModel.updateFiltersFull(
                        viewModel.getFiltersFull().copy(
                            ratingFrom = values[0], ratingTo = values[1]
                        )
                    )
                }
            }
        })
    }

    private fun setFilterSorting() {
        binding.searchRadioGroupSorting.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.searchRadioSortingDate.id ->
                    viewModel.updateFiltersFull(
                        viewModel.getFiltersFull().copy(order = SORTING_PARAMS[2]))
                binding.searchRadioSortingPopular.id ->
                    viewModel.updateFiltersFull(
                        viewModel.getFiltersFull().copy(order = SORTING_PARAMS[1]))
                else -> viewModel.updateFiltersFull(
                    viewModel.getFiltersFull().copy(order = SORTING_PARAMS[0]))
            }
        }
    }

    private fun onClickYearPiker() {
        binding.searchSettingsYearTv.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSearchSettings_to_fragmentSearchYearChoose)
        }
    }

    private fun onClickFilterCountryGenreChoose() {
        binding.searchSettingsCountryTv.setOnClickListener {
            val action = FragmentSearchSettingsDirections
                .actionFragmentSearchSettingsToFragmentSearchFilters(FragmentSearchFilters.KEY_COUNTRY)
            findNavController().navigate(action)
        }
        binding.searchSettingsGenreTv.setOnClickListener {
            val action = FragmentSearchSettingsDirections
                .actionFragmentSearchSettingsToFragmentSearchFilters(FragmentSearchFilters.KEY_GENRE)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}