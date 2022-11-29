package com.example.skillbox_hw_quiz.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.skillbox_hw_quiz.databinding.CustomViewQuestionsBinding

import com.example.skillbox_hw_quiz.quiz.QuizStorage

class CustomView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    val bining = CustomViewQuestionsBinding.inflate(LayoutInflater.from(context))

    init {
        addView(bining.root)
    }
    fun setQuestions() {
        bining.firstTextQuestion.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].question
        bining.firstFirstRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].answers[0]
        bining.firstSecondRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].answers[1]
        bining.firstThirdRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].answers[2]
        bining.firstFourthRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].answers[3]

        bining.secondTextQuestion.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].question
        bining.secondFirstRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].answers[0]
        bining.secondSecondRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].answers[1]
        bining.secondThirdRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].answers[2]
        bining.secondFouthRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].answers[3]

        bining.thirdTextQuestion.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].question
        bining.thirdFirstRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].answers[0]
        bining.thirdSecondRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].answers[1]
        bining.thirdThirdRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].answers[2]
        bining.thirdFourthRadio.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].answers[3]
    }

}