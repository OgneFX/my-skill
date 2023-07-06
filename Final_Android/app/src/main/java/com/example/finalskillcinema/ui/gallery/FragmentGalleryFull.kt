package com.example.finalskillcinema.ui.gallery

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.finalskillcinema.R
import com.example.finalskillcinema.data.GALLERY_TYPES
import com.example.finalskillcinema.databinding.FragmentFilmGalleryBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.finalskillcinema.ui.adapters.MyAdapterTypes
import com.example.finalskillcinema.ui.adapters.MyListAdapter


@AndroidEntryPoint
class FragmentGalleryFull : Fragment() {
    private var _binding: FragmentFilmGalleryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelGallery by viewModels()
    private lateinit var galleryAdapter: MyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmGalleryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: FragmentGalleryFullArgs by navArgs()
        viewModel.setGallery(args.filmId)

        setGalleryAdapter(args.filmId)                              // set adapter
        stateListener()                                             // set loading listener
        setChipButton()                                             // set Chip-group and image list

        binding.galleryBackBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setGalleryAdapter(filmId: Int) {
        val gridManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                .apply {
                    spanSizeLookup = object : SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return if (position % 3 == 0) 2 else 1
                        }
                    }
                }

//        val currentCategory = viewModel.getCurrentCategory()
        galleryAdapter = MyListAdapter(null, {}) {
            onClickItem(
                position = it,
                filmId = filmId
            )
        }
        binding.filmGalleryPager.layoutManager = gridManager
        binding.filmGalleryPager.adapter = galleryAdapter
    }

    private fun setChipButton() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.galleryChipList.collect {
                    val chipGroup = ChipGroup(requireContext()).apply {
                        isSingleSelection = true
                        chipSpacingHorizontal = 8
                    }
                    it.forEach { (key, value) ->
                        if (value != 0) {
                            val nameChip = GALLERY_TYPES[key]
                            val chip = Chip(requireContext()).apply {
                                text = resources.getString(R.string.chip_name, nameChip, value)
                                chipBackgroundColor = chipBackColors
                                setTextColor(chipTextColors)
                                chipStrokeColor = chipStrokeColors
                                isCheckable = true
                                checkedIcon = null
                                transitionName = key
                                chipStrokeWidth = 1f
                                isSelected = false
                            }
                            chip.setOnClickListener { myChip ->
                                viewModel.updateGalleryType(galleryType = myChip.transitionName)
                                binding.filmGalleryPager.invalidate()
                            }
                            if (chipGroup.size == 0) {
                                chip.isChecked = true
                                setGalleryImages(chip.transitionName)
                                binding.filmGalleryPager.invalidate()
                            }
                            chipGroup.addView(chip)
                        }
                    }
                    binding.galleryChipsGroupContainer.addView(chipGroup)
                }
            }
        }
    }

    private fun setGalleryImages(galleryType: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.updateGalleryType(galleryType = galleryType)
                viewModel.images.collect {
                    galleryAdapter.submitList(it.map { image ->
                        MyAdapterTypes.ItemGalleryImage(
                            image
                        )
                    })
                }
            }
        }
    }

    private fun stateListener() {
//        galleryAdapter.addLoadStateListener { state ->
//            when (state.refresh) {
//                is LoadState.Loading -> {
//                    binding.galleryChipScrollGroup.isVisible = false
//                    binding.filmGalleryPager.isVisible = false
//                    binding.galleryProgressGroup.isVisible = true
//                    binding.galleryLoadingRefreshBtn.isVisible = false
//                }
//                is LoadState.NotLoading -> {
//                    binding.galleryChipScrollGroup.isVisible = true
//                    binding.filmGalleryPager.isVisible = true
//                    binding.galleryProgressGroup.isVisible = false
//                    binding.galleryLoadingRefreshBtn.isVisible = true
//                }
//                is LoadState.Error -> {
//                    binding.galleryChipScrollGroup.isVisible = false
//                    binding.filmGalleryPager.isVisible = false
//                    binding.galleryProgressGroup.isVisible = false
//                    binding.galleryLoadingRefreshBtn.isVisible = true
//                }
//            }
//        }
    }

    private fun onClickItem(position: Int, filmId: Int) {
        val currentGalleryType = viewModel.getCurrentCategory()
        val action = FragmentGalleryFullDirections
            .actionFragmentGalleryToFragmentGalleryFullscreen(
                position = position,
                galleryType = currentGalleryType,
                filmId = filmId
            )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearChipMap()
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