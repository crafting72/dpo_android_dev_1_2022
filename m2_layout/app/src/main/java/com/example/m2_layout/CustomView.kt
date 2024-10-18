package com.example.m2_layout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.m2_layout.databinding.APostFromASocialNetworkBinding

class CustomView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = APostFromASocialNetworkBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun setUpText(text: String){
        binding.upText.text = text
    }

    fun setDownText(text: String){
        binding.downText.text = text
    }
}