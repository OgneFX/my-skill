package com.example.finalskillcinema.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finalskillcinema.R
import com.example.finalskillcinema.data.ParamsFilterFilm
import com.example.finalskillcinema.databinding.FragmentSearchBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import com.example.finalskillcinema.ui.search.filmadapter.SearchFilmAdapter
import com.example.finalskillcinema.ui.search.personadapter.PersonAdapter
import kotlinx.coroutines.flow.collectLatest


class Search : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelSearch by activityViewModels()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        SearchFilmAdapter { onFilmClick(it) }
    }
    private val personAdapter by lazy(LazyThreadSafetyMode.NONE) {
        PersonAdapter { onPersonClick(it) }
    }
    private val myMergeAdapter = ConcatAdapter(personAdapter, adapter)

    private var isEditFocused = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()                // set adapter
        setSearchString()           // set search string
        getFilmList()               // set film list

        binding.searchFilterBtn.setOnClickListener { setSearchMenu(it) }
    }

    private fun onFilmClick(filmId: Int) {
        Log.d(TAG, "onFilmClick: filmId = $filmId")
        val action = SearchDirections.actionFragmentSearchToFragmentFilmDetail(filmId)
        findNavController().navigate(action)
    }

    private fun onPersonClick(personId: Int) {
        val action = SearchDirections.actionFragmentSearchToFragmentPersonDetail(personId)
        findNavController().navigate(action)
    }

    private fun setAdapter() {
        adapter.addLoadStateListener { state ->
            val currentState = state.refresh
            binding.searchFilmList.isVisible = currentState != LoadState.Loading
            binding.loadingProgress.isVisible = currentState == LoadState.Loading
            binding.searchProgressText.isVisible = currentState != LoadState.Loading
            binding.searchProgressImage.isVisible = currentState == LoadState.Loading
            when (currentState) {
                is LoadState.Loading -> {
                    binding.searchFilmList.isVisible = false
                    binding.searchProgressGroup.isVisible = true
                    binding.searchProgressText.isVisible = false
                    binding.searchProgressImage.isVisible = true
                }
                is LoadState.NotLoading -> {
                    binding.searchFilmList.isVisible = true
                    binding.loadingProgress.isVisible = false
                    binding.searchProgressText.isVisible = false
                    binding.searchProgressImage.isVisible = false

                }
                else -> {
                    binding.searchFilmList.isVisible = false
                    binding.loadingProgress.isVisible = false
                    binding.searchProgressText.isVisible = true
                    binding.searchProgressImage.isVisible = true
                }
            }
        }
        binding.searchFilmList.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.searchFilmList.adapter = myMergeAdapter
    }

    private fun setSearchString() {
        binding.searchMyField.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            binding.searchGroup.background = if (hasFocus) {
                isEditFocused = true
                ResourcesCompat.getDrawable(
                    resources, R.drawable.search_input_field_select, null
                )
            } else {
                ResourcesCompat.getDrawable(resources, R.drawable.search_input_field, null)
            }
        }

        binding.searchClearBtn.setOnClickListener {
            binding.searchMyField.text?.clear()
            val newFilter = viewModel.getFiltersFull().copy(keyword = "")
            viewModel.updateFiltersFull(filterFilm = newFilter)
        }

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.searchClearBtn.isVisible = viewModel.getFiltersFull().keyword.isNotEmpty()
                binding.searchMyField.textChanges()
                    .collect { state ->
                        val keyword = binding.searchMyField.text.toString()
                        Log.d("FragmentSearch", "setSearchString: keyword = $keyword")
                        if (state) {
                            val newFilter = viewModel.getFiltersFull().copy(keyword = keyword)
                            viewModel.updateFiltersFull(filterFilm = newFilter)
                        }
                    }
            }
        }
    }

    private fun getFilmList() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filterFlow.collect {
                    personAdapter.refresh()
                    adapter.refresh()
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.films.collectLatest(adapter::submitData)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.persons.collect {
                    personAdapter.submitData(it)
                }
            }
        }
    }

    private fun setSearchMenu(view: View) {
        val popUpMenu = PopupMenu(requireContext(), view)
        popUpMenu.inflate(R.menu.search_menu)

        popUpMenu
            .setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.search_settings_set -> {
                        findNavController()
                            .navigate(R.id.action_fragmentSearch_to_fragmentSearchSettings)
                        true
                    }
                    R.id.search_filters_clear -> {
                        viewModel.updateFiltersFull(ParamsFilterFilm())
                        binding.searchMyField.text?.clear()
                        Toast.makeText(
                            requireActivity().applicationContext,
                            getString(R.string.search_clear_message),
                            Toast.LENGTH_SHORT
                        ).show()
                        true
                    }
                    else -> false
                }
            }

        popUpMenu.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun EditText.textChanges(): Flow<Boolean> {
        return callbackFlow {
            val watcher = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    trySend(false)
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    binding.searchClearBtn.isVisible = !s.isNullOrEmpty()
                    viewLifecycleOwner.lifecycleScope.launch {
                        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                            trySend(false)
                            delay(500)
                            trySend(true)
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            }
            addTextChangedListener(watcher)
            awaitClose { removeTextChangedListener(watcher) }
        }.onStart { emit(false) }
    }

    companion object {
        private const val TAG = "FragmentSearch"
    }
}