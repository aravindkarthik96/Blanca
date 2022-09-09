package com.aravindkarthik.blanca.settings

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.aravindkarthik.blanca.R
import com.aravindkarthik.blanca.databinding.BlancaSettingsLayoutBinding
import com.aravindkarthik.blanca.help.HelpViewAdapter
import com.aravindkarthik.blanca.lang.core.Function

class SettingsView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private val binding = BlancaSettingsLayoutBinding.inflate(LayoutInflater.from(context),this)
    fun setup(functions: List<Function>) {
        binding.closeButton.setOnClickListener {
            (context as AppCompatActivity).onBackPressed()
        }
    }
}