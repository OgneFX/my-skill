package com.example.skillbox_hw_quiz.ui.main

import android.app.ActivityOptions
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentFirstBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat

import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HelloFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val calendar = Calendar.getInstance()
    val dataFormat = SimpleDateFormat("dd-MM-yyyy")

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirstSecond.setOnClickListener {
            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setTitleText(resources.getString(R.string.choose_the_data_title))
                .build()

                dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                    calendar.timeInMillis = timeInMillis
                    Snackbar.make(
                        binding.buttonFirstSecond,
                        dataFormat.format(calendar.time),
                        Snackbar.LENGTH_SHORT
                    ).show()

                }
            dateDialog.show(childFragmentManager.beginTransaction(), "11")
        }


        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}