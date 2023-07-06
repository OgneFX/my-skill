package com.example.finalskillcinema.ui.profile

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalskillcinema.R
import com.example.finalskillcinema.app.prepareToShow
import com.example.finalskillcinema.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.finalskillcinema.ui.profile.adapter.CollectionDB
import com.example.finalskillcinema.ui.profile.adapter.ProfileAdapter
import com.example.finalskillcinema.ui.profile.adapter.ProfileAdapterTypes


@AndroidEntryPoint
class FragmentProfile : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterFilmsViewed: ProfileAdapter
    private lateinit var adapterFilmsCache: ProfileAdapter
    private lateinit var adapterCollections: ProfileAdapter

    private val viewModel: ViewModelProfile by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapters()
        setCollectionsList()

        binding.profileGroupAddCollection.setOnClickListener {
            createNewCollectionDialog(requireContext())
        }
    }

    private fun setAdapters() {
        adapterFilmsViewed =
            ProfileAdapter(COLLECTION_SIZE, { onClickClearHistory("VIEWED") }, { onFilmClick(it) })
        binding.profileListViewed.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.profileListViewed.adapter = adapterFilmsViewed

        adapterFilmsCache =
            ProfileAdapter(COLLECTION_SIZE, { onClickClearHistory("CACHE") }, { onFilmClick(it) })
        binding.profileListFavorite.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.profileListFavorite.adapter = adapterFilmsCache

        adapterCollections = ProfileAdapter(COLLECTION_SIZE, {}, {})
        binding.profileListCollections.layoutManager =
            GridLayoutManager(
                requireActivity().applicationContext,
                2,
                GridLayoutManager.HORIZONTAL,
                false
            )
        binding.profileListCollections.adapter = adapterCollections
    }

    private fun onClickClearHistory(collectionName: String) {
        viewModel.clearCacheCollection(collectionName)
    }

    private fun setCollectionsList() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filmsViewed.collect { films ->
                    if (films.isNotEmpty()) {
                        binding.profileLabelViewed.isVisible = true
                        binding.profileListViewed.isVisible = true
                        adapterFilmsViewed.submitList(
                            films.map { ProfileAdapterTypes.ItemDB(it) }
                                .prepareToShow(COLLECTION_SIZE)
                        )
                    } else {
                        binding.profileLabelViewed.isVisible = false
                        binding.profileListViewed.isVisible = false
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allFilms.collect { films ->
                    if (films.isNotEmpty()) {
                        binding.profileLabelFavorite.isVisible = true
                        binding.profileListFavorite.isVisible = true
                        adapterFilmsCache.submitList(
                            films.map { ProfileAdapterTypes.ItemDB(it) }
                                .prepareToShow(COLLECTION_SIZE)
                        )
                    } else {
                        binding.profileLabelFavorite.isVisible = false
                        binding.profileListFavorite.isVisible = false
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.collectionsList.collect { collections ->
                    if (collections.isNotEmpty()) {
                        val newCollectionList = mutableListOf<ProfileAdapterTypes.ItemCollection>()
                        collections.forEach {
                            val icon = when (it.collectionName) {
                                "Любимые" -> R.drawable.ic_favorite
                                "Хочу посмотреть" -> R.drawable.ic_bookmark
                                else -> R.drawable.ic_user_collection
                            }
                            newCollectionList.add(
                                ProfileAdapterTypes.ItemCollection(
                                CollectionDB(it.collectionName, 0, icon)
                            ))
                        }
                        adapterCollections.submitList(newCollectionList.toList())
                    }
                }
            }
        }
    }

    private fun createNewCollectionDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_create_collection, null)

        val editTextCollectionName = dialogView.findViewById(R.id.et_add_collection) as EditText
        val btnAddCollection = dialogView.findViewById(R.id.btn_add_collection) as Button

        builder.setView(dialogView)

        val dialog = builder.create()

        btnAddCollection.setOnClickListener {
            val text = editTextCollectionName.text.toString()
            viewModel.addNewCollection(text)
            Toast
                .makeText(context, "Добавлена новая коллекция: $text", Toast.LENGTH_SHORT)
                .show()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun onFilmClick(filmId: Int) {
        val action =
            FragmentProfileDirections.actionFragmentProfileToFragmentFilmDetail(filmId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val COLLECTION_SIZE = 10
    }
}