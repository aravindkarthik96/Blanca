package com.aravindkarthik.blanca.help

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.aravindkarthik.blanca.databinding.BlancaHelpLayoutBinding
import com.aravindkarthik.blanca.lang.core.Function

class HelpView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private val binding = BlancaHelpLayoutBinding.inflate(LayoutInflater.from(context), this)

    fun setup(functions: List<Function>) {
        binding.codeList.adapter = HelpViewAdapter(functions)
        binding.closeButton.setOnClickListener {
            (context as AppCompatActivity).onBackPressed()
        }
    }
}