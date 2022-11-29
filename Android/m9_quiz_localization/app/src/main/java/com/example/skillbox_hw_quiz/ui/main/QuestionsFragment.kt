package com.example.skillbox_hw_quiz.ui.main

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.app.usage.UsageEvents.Event.NONE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentSecondBinding
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import com.google.android.material.snackbar.Snackbar
import java.util.*



/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class QuestionsFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root


    }

    private fun setQuestionsRU() {
        binding.firstTextQuestion.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].question
        binding.firstFirstRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].answers[0]
        binding.firstSecondRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].answers[1]
        binding.firstThirdRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].answers[2]
        binding.firstFourthRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].answers[3]

        binding.secondTextQuestion.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].question
        binding.secondFirstRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].answers[0]
        binding.secondSecondRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].answers[1]
        binding.secondThirdRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].answers[2]
        binding.secondFouthRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].answers[3]

        binding.thirdTextQuestion.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].question
        binding.thirdFirstRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].answers[0]
        binding.thirdSecondRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].answers[1]
        binding.thirdThirdRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].answers[2]
        binding.thirdFourthRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].answers[3]
    }
    private fun setQuestionsEN() {
        binding.firstTextQuestion.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].question
        binding.firstFirstRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].answers[0]
        binding.firstSecondRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].answers[1]
        binding.firstThirdRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].answers[2]
        binding.firstFourthRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].answers[3]

        binding.secondTextQuestion.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].question
        binding.secondFirstRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].answers[0]
        binding.secondSecondRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].answers[1]
        binding.secondThirdRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].answers[2]
        binding.secondFouthRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].answers[3]

        binding.thirdTextQuestion.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].question
        binding.thirdFirstRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].answers[0]
        binding.thirdSecondRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].answers[1]
        binding.thirdThirdRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].answers[2]
        binding.thirdFourthRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].answers[3]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.firstTextQuestion.animate().apply {
            duration = 3000
            alpha(1f)
            interpolator = AccelerateInterpolator()
        }.start()


        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }



        var firstLine = " "
        var secondLine = " "
        var thirdLine = " "
        if (Locale.getDefault().language == "ru")
        {   setQuestionsRU()
            binding.firstRadio.setOnCheckedChangeListener { _, i ->
            when (i)
            {
                R.id.first_first_radio -> firstLine = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].feedback[0]
                R.id.first_second_radio -> firstLine = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].feedback[1]
                R.id.first_third_radio -> firstLine = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].feedback[2]
                R.id.first_fourth_radio -> firstLine = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].feedback[3]
            }
        }
            binding.secondRadio.setOnCheckedChangeListener { _, i ->
                when (i)
                {
                    R.id.second_first_radio -> secondLine = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].feedback[0]
                    R.id.second_second_radio -> secondLine = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].feedback[1]
                    R.id.second_third_radio -> secondLine = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].feedback[2]
                    R.id.second_fouth_radio -> secondLine = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].feedback[3]
                }
            }
            binding.thirdRadio.setOnCheckedChangeListener { _, i ->
                when (i)
                {
                    R.id.third_first_radio -> thirdLine = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].feedback[0]
                    R.id.third_second_radio -> thirdLine = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].feedback[1]
                    R.id.third_third_radio -> thirdLine = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].feedback[2]
                    R.id.third_fourth_radio -> thirdLine = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].feedback[3]
                }
            }}
        else
        {
            setQuestionsEN()
            binding.firstRadio.setOnCheckedChangeListener { _, i ->
                when (i)
                {
                    R.id.first_first_radio -> firstLine = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].feedback[0]
                    R.id.first_second_radio -> firstLine = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].feedback[1]
                    R.id.first_third_radio -> firstLine = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].feedback[2]
                    R.id.first_fourth_radio -> firstLine = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].feedback[3]
                }
            }
            binding.secondRadio.setOnCheckedChangeListener { _, i ->
                when (i)
                {
                    R.id.second_first_radio -> secondLine = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].feedback[0]
                    R.id.second_second_radio -> secondLine = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].feedback[1]
                    R.id.second_third_radio -> secondLine = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].feedback[2]
                    R.id.second_fouth_radio -> secondLine = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].feedback[3]
                }
            }
            binding.thirdRadio.setOnCheckedChangeListener { _, i ->
                when (i)
                {
                    R.id.third_first_radio -> thirdLine = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].feedback[0]
                    R.id.third_second_radio -> thirdLine = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].feedback[1]
                    R.id.third_third_radio -> thirdLine = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].feedback[2]
                    R.id.third_fourth_radio -> thirdLine = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].feedback[3]
                }
            }

        }




        binding.checkResult.setOnClickListener {

            val bundle = Bundle().apply {
                putString("param1", firstLine )
                putString("param2", secondLine )
                putString("param3", thirdLine )
            }
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment, args = bundle)

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}