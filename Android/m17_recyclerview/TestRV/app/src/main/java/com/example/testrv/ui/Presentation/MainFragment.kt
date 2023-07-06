package com.example.testrv.ui.Presentation


import Data.Photos
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testrv.R


import com.example.testrv.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private val firstAdapter = FirstAdapter { Photos -> onItemClick(Photos) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = firstAdapter

        viewModel.nasaData.onEach {
            firstAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun onItemClick(item: Photos) {
        val bundle = Bundle().apply {
            putString("param1", item.imgSrc)
            putString("param2", item.rover?.name)
            putString("param3", item.camera?.name)
            putString("param4", item.sol.toString())
            putString("param5", item.earthDate.toString())
        }
        findNavController().navigate(R.id.action_mainFragment_to_fullScreenFragment, args = bundle)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}