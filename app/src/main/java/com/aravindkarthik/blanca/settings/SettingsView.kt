package com.aravindkarthik.blanca.settings

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.aravindkarthik.blanca.R
import com.aravindkarthik.blanca.help.HelpViewAdapter
import com.aravindkarthik.blanca.lang.core.Function
import kotlinx.android.synthetic.main.blanca_help_layout.view.*

class SettingsView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private val view: View = LayoutInflater.from(context).inflate(R.layout.blanca_settings_layout, this, true)

    fun setup(functions: List<Function>) {
        view.codeList.adapter = HelpViewAdapter(functions)
        view.closeButton.setOnClickListener {
            (context as AppCompatActivity).onBackPressed()
        }
    }
}