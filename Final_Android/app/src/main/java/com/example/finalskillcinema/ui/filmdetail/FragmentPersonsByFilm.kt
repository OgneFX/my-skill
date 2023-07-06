package com.example.finalskillcinema.ui.filmdetail

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalskillcinema.R
import com.example.finalskillcinema.databinding.FragmentPersonAllByFilmBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.finalskillcinema.ui.adapters.MyAdapterTypes
import com.example.finalskillcinema.ui.adapters.MyListAdapter
import com.example.finalskillcinema.ui.states.StateInfo


@AndroidEntryPoint
class FragmentPersonsByFilm : Fragment() {
    private var _binding: FragmentPersonAllByFilmBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelStaffsByFilm by viewModels()
    private lateinit var adapter: MyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonAllByFilmBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filmId = requireArguments().getInt(KEY_ALL_STAFF_FILM)
        val profession = requireArguments().getString(KEY_ALL_STAFF_PROFESSION)

        if (profession != null) {
            viewModel.getStaffsByFilm(filmId, profession)
            stateLoadingListener(filmId, profession)                // set loading listener
            setStaffList(profession)                                // set persons list
        }

        binding.allStaffsBackBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setStaffList(professionKey: String) {
        adapter = MyListAdapter(
            maxListSize = null,
            clickItem = { onItemClick(it) },
            clickEndButton = {}
        )
        binding.allStaffsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.allStaffsList.adapter = adapter

        when (professionKey) {
            "ACTOR" -> binding.allStaffsCategoryTv.text =
                    resources.getString(R.string.label_film_actors)
            else -> binding.allStaffsCategoryTv.text =
                    resources.getString(R.string.label_film_makers)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.persons.collect { makersList ->
                    adapter.submitList(makersList.map { MyAdapterTypes.ItemFilmPerson(it) })
                }
            }
        }
    }

    private fun stateLoadingListener(filmId: Int, professionKey: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadCategoryState.collect { state ->
                    when (state) {
                        is StateInfo.Loading -> {
                            binding.progressGroup.isVisible = true
                            binding.loadingProgress.isVisible = true
                            binding.loadingRefreshBtn.isVisible = false
                            binding.allStaffsList.isVisible = false
                        }
                        is StateInfo.Success -> {
                            binding.progressGroup.isVisible = false
                            binding.allStaffsList.isVisible = true
                        }
                        else -> {
                            binding.progressGroup.isVisible = true
                            binding.loadingProgress.isVisible = false
                            binding.loadingRefreshBtn.isVisible = true
                            binding.allStaffsList.isVisible = false
                            binding.loadingRefreshBtn.setOnClickListener {
                                viewModel.getStaffsByFilm(filmId, professionKey)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onItemClick(personId: Int) {
        val action =
            FragmentPersonsByFilmDirections.actionFragmentPersonsByFilmToFragmentPersonDetail(personId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_ALL_STAFF_FILM = "KEY_ALL_STAFF_FILM"
        const val KEY_ALL_STAFF_PROFESSION = "KEY_ALL_STAFF_PROFESSION"
    }
}