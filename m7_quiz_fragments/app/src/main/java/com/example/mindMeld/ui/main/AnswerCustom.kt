package com.example.mindMeld.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.mindMeld.databinding.CustomAnswerBinding
import com.example.mindMeld.quiz.Question

class AnswerCustom @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?=null,
    defStyleAttr: Int=0
): LinearLayout(context, attrs, defStyleAttr) {

    private val binding = CustomAnswerBinding.inflate(LayoutInflater.from(context))
    var feedback: Int = -1

    init {
        addView(binding.root)
    }

    fun editText(quiz: Question) {
        binding.optAnswer1.text = quiz.answers[0]
        binding.optAnswer2.text = quiz.answers[1]
        binding.optAnswer3.text = quiz.answers[2]
        binding.optAnswer4.text = quiz.answers[3]
        binding.textQuestion.text = quiz.question
    }

    fun setOnCheckedChangeListener() {
        binding.radioGroupQuestion.setOnCheckedChangeListener { _, i ->
            feedback = when (i) {
                binding.optAnswer1.id -> 0
                binding.optAnswer2.id -> 1
                binding.optAnswer3.id -> 2
                binding.optAnswer4.id -> 3
                else -> -1
            }
        }
    }
}