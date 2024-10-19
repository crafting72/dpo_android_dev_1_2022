package com.example.mindMeld.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mindMeld.R
import com.example.mindMeld.databinding.FragmentSecondBinding
import com.example.mindMeld.quiz.QuizStorage

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val locale = QuizStorage.Locale.En
    private val question = QuizStorage.getQuiz(locale).questions

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.answer1.editText(question[0])
        binding.answer2.editText(question[1])
        binding.answer3.editText(question[2])

        binding.answer1.setOnCheckedChangeListener()
        binding.answer2.setOnCheckedChangeListener()
        binding.answer3.setOnCheckedChangeListener()

        anim(binding.answer1)
        anim(binding.answer2)
        anim(binding.answer3)
        anim(binding.resultButton)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.resultButton.setOnClickListener {
            if (binding.answer1.feedback == -1) binding.answer1.feedback = 0
            if (binding.answer2.feedback == -1) binding.answer2.feedback = 3
            if (binding.answer3.feedback == -1) binding.answer3.feedback = 3
            findNavController().navigate(
                SecondFragmentDirections.actionSecondFragmentToThirdFragment(
                    QuizStorage.answer(QuizStorage.getQuiz(locale),
                        listOf(binding.answer1.feedback,
                            binding.answer2.feedback,
                            binding.answer3.feedback))))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun anim(view: View){
        view.alpha = 0f
        view.animate().apply {
            duration = 1000
            alpha(1f)
        }.start()
    }
}