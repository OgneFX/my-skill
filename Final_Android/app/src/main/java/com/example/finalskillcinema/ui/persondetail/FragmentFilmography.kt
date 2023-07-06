package com.example.finalskillcinema.ui.persondetail

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finalskillcinema.data.PROFESSIONS
import com.example.finalskillcinema.databinding.FragmentPersonFilmographyBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.finalskillcinema.db.model.PersonFilms
import com.example.finalskillcinema.ui.adapters.MyAdapterTypes
import com.example.finalskillcinema.ui.adapters.MyListAdapter
import com.example.finalskillcinema.ui.states.StateInfo


@AndroidEntryPoint
class FragmentFilmography : Fragment() {
    private var _binding: FragmentPersonFilmographyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelPersonFilmography by viewModels()
    private lateinit var adapter: MyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonFilmographyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: FragmentFilmographyArgs by navArgs()
        viewModel.getFilmsByPerson(args.personId)

        setLoadingStateListener(args.personId)                      // set loading listener
        setAdapter()                                                // set adapter
        setFilmList()                                               // set film list

        binding.filmographyBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setLoadingStateListener(personId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadCurrentStaff.collect { state ->
                    when (state) {
                        is StateInfo.Loading -> {
                            binding.apply {
                                filmographyProgressGroup.isVisible = true
                                filmographyLoadingProgress.isVisible = true
                                filmographyLoadingBanner.isVisible = true
                                filmographyLoadingRefreshBtn.isVisible = false

                                filmographyList.isVisible = false
                                filmographyChipGroup.isVisible = false
                            }
                        }

                        is StateInfo.Success -> {
                            setChipButton()
                            binding.apply {
                                filmographyProgressGroup.isVisible = false
                                filmographyLoadingProgress.isVisible = false
                                filmographyLoadingBanner.isVisible = false
                                filmographyLoadingRefreshBtn.isVisible = false

                                filmographyList.isVisible = true
                                filmographyChipGroup.isVisible = true
                            }
                        }

                        else -> {
                            binding.apply {
                                filmographyProgressGroup.isVisible = true
                                filmographyLoadingProgress.isVisible = true
                                filmographyLoadingBanner.isVisible = true
                                filmographyLoadingRefreshBtn.isVisible = false

                                filmographyList.isVisible = false
                                filmographyChipGroup.isVisible = false

                                filmographyLoadingRefreshBtn.setOnClickListener {
                                    viewModel.getFilmsByPerson(personId)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        adapter = MyListAdapter(null, {}) { onFilmClick(it) }
        binding.filmographyList.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.filmographyList.adapter = adapter
    }

    private fun setFilmList() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.films.collect { films ->
                    adapter.submitList(films.map { MyAdapterTypes.ItemFilmShortInfo(it) })
                }
            }
        }
    }

    private fun onFilmClick(filmId: Int) {
        val action =
            FragmentFilmographyDirections.actionFragmentFilmographyToFragmentFilmDetail(filmId)
        findNavController().navigate(action)
    }

    private fun setChipButton() {
        val filmList = viewModel.getAllFilmWithProfession()
        val chipGroup = ChipGroup(requireContext()).apply {
            isSingleSelection = true
            chipSpacingHorizontal = 8
        }
        val professionList = getProfessionList(filmList)
        professionList.forEach { profession ->
            val chip = Chip(requireContext()).apply {
                text = PROFESSIONS.getValue(profession)
                transitionName = profession
                chipBackgroundColor = chipBackColors
                setTextColor(chipTextColors)
                chipStrokeColor = chipStrokeColors
                isCheckable = true
                checkedIcon = null
                chipStrokeWidth = 1f
                isSelected = false
                isChecked = chipGroup.size == 0
            }
            chip.setOnClickListener { myChip ->
                viewModel.getFilmsByCurrentProfession(myChip.transitionName)
                binding.filmographyList.invalidate()
            }
            chipGroup.addView(chip)
        }
        binding.filmographyChipsGroupContainer.addView(chipGroup)
    }

    private fun getProfessionList(filmList: List<PersonFilms>): List<String> {
        val tempList = mutableSetOf<String>()
        filmList.forEach { film ->
            if (PROFESSIONS.containsKey(film.professionKey)) film.professionKey?.let {
                tempList.add(it)
            }
        }
        return tempList.toList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val chipBackColors = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked, android.R.attr.state_enabled),
                intArrayOf()
            ),
            intArrayOf(Color.BLUE, Color.WHITE)
        )
        val chipTextColors = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked, android.R.attr.state_enabled),
                intArrayOf()
            ),
            intArrayOf(Color.WHITE, Color.BLACK)
        )
        val chipStrokeColors = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked, android.R.attr.state_enabled),
                intArrayOf()
            ),
            intArrayOf(Color.BLUE, Color.BLACK)
        )
    }
}