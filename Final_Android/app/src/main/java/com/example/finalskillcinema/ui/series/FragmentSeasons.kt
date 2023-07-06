package com.example.finalskillcinema.ui.series

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalskillcinema.R
import com.example.finalskillcinema.databinding.FragmentSeasonsBinding
import com.example.finalskillcinema.db.model.SeasonEpisode
import com.example.finalskillcinema.ui.adapters.MyAdapterTypes
import com.example.finalskillcinema.ui.adapters.MyListAdapter
import com.example.finalskillcinema.ui.states.StateInfo
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FragmentSeasons : Fragment() {

    private var _binding: FragmentSeasonsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelSeasons by viewModels()
    private lateinit var adapter: MyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeasonsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seriesId = requireArguments().getInt(KEY_SEASON_ID)
        val seriesName = requireArguments().getString(KEY_SERIES_NAME)

        viewModel.getSeasons(seriesId)

        setAdapter()                            // set adapter
        getEpisodeList(seriesId)                // get episodes list and set chip-group

        binding.seriesNameTv.text = seriesName
        binding.seriesBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setAdapter() {
        adapter = MyListAdapter(null, {}, {})
        binding.seriesEpisodeList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.seriesEpisodeList.adapter = adapter
    }

    private fun getEpisodeList(seriesId: Int) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadCategoryState.collect { state ->
                    when(state) {
                        is StateInfo.Success -> setEpisodeList()
                        is StateInfo.Error -> viewModel.getSeasons(seriesId)
                        else -> {}
                    }
                }
            }
        }
    }

    private fun setEpisodeList() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.seasons.collect { seasons ->
                    val tempSeasons: List<Int> = seasons.map { it.seriesNumber }.toSet().toList()
                    val tempEpisodes: List<SeasonEpisode> = seasons.filter { it.seriesNumber == tempSeasons[0] }
                    binding.seasonEpisodeCount.text =
                        getSeasonLabel(tempSeasons[0], tempEpisodes.size)
                    adapter.submitList(tempEpisodes.map { MyAdapterTypes.ItemEpisode(it) })
                    setChipGroup(seasons)
                }
            }
        }
    }

    private fun setChipGroup(seasons: List<SeasonEpisode>) {
        val tempSeasons = seasons.map { it.seriesNumber }.toSet().toList()
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
        val chipGroup = ChipGroup(requireContext()).apply {
            isSingleSelection = true
            chipSpacingHorizontal = 8
        }
        for (i in tempSeasons.indices) {
            val chip = Chip(requireContext()).apply {
                text = resources.getString(R.string.season_chip_name, i + 1)
                chipBackgroundColor = chipBackColors
                setTextColor(chipTextColors)
                chipStrokeColor = chipStrokeColors
                chipStartPadding = 24f
                chipEndPadding = 24f
                isCheckable = true
                checkedIcon = null
                chipStrokeWidth = 1f
                isSelected = false
                isChecked = chipGroup.size == 0
            }
            chip.setOnClickListener {
                val tempEpisodes: List<SeasonEpisode> = seasons.filter { it.seriesNumber == tempSeasons[i] }
                binding.seasonEpisodeCount.text =
                    getSeasonLabel(tempSeasons[i], tempEpisodes.size)
                adapter.submitList(tempEpisodes.map { MyAdapterTypes.ItemEpisode(it) })
            }
            chipGroup.addView(chip)
        }
        binding.seriesChipsGroupContainer.addView(chipGroup)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getSeasonLabel(seasonNumber: Int, episodeCount: Int): String {
        val episodeStr = resources.getQuantityString(
            R.plurals.film_details_episode_count,
            episodeCount,
            episodeCount
        )
        return resources.getString(
            R.string.episodes_count,
            seasonNumber,
            episodeStr
        )
    }

    companion object {
        const val KEY_SEASON_ID = "KEY_SEASON_ID"
        const val KEY_SERIES_NAME = "KEY_SERIES_NAME"
    }
}