package com.example.mindMeld.ui.main

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
//import android.animation.PropertyValuesHolder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
//import android.view.animation.AnimationSet
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mindMeld.R
import com.example.mindMeld.databinding.FragmentThirdBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        binding.lottieView.playAnimation()
        (AnimatorInflater.loadAnimator(context, R.animator.button_back_on_result)
                as ObjectAnimator).apply {
            target = binding.backButton
            start()
        }
        val firstAnim = ObjectAnimator.ofFloat(binding.firstResult,
            "alpha",
            0f,
            1f).apply {
            duration = 7000
        }
        val secondAnim = ObjectAnimator.ofFloat(binding.firstResult,View.ROTATION_X,0f,720f)
            .apply {
                duration = 2000
                interpolator = AccelerateDecelerateInterpolator()
            }
        AnimatorSet().apply {
            play(secondAnim).after(firstAnim)
            start()
        }
        //(AnimatorInflater.loadAnimator(context, R.animator.animation_text_result)
        //   as ObjectAnimator).apply {
        // target = binding.firstResult
        //   start()
        // }
        //  binding.lottieView.animate().apply {
        //       duration = 2000
        //      alpha(0f)
        //   }.start()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: ThirdFragmentArgs by navArgs()
        val result = args.listAns
        binding.firstResult.text = result
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}