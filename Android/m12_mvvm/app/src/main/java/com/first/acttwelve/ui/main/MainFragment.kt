package com.first.acttwelve.ui.main


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.first.acttwelve.databinding.FragmentMainBinding


class MainFragment : Fragment() {


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            viewModel.searchButtonActive()
        }
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                viewModel.searchButton(p3)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })




        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

            viewModel.state.collect { state: State ->
                when (state) {
                    State.Ready -> {

                        binding.button.isEnabled = true
                        binding.progress.isVisible = false
                    }
                    State.NotReady -> {
                        binding.button.isEnabled = false
                        binding.progress.isVisible = false
                    }

                    State.Loading -> {
                        binding.button.isEnabled = false
                        binding.progress.isVisible = true
                        binding.message.text =
                            "По запросу ${binding.editText.text} ничего не найдено"
                    }


                }

            }
        }


    }


}