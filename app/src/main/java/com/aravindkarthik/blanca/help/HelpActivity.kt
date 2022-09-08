package com.aravindkarthik.blanca.help

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aravindkarthik.blanca.databinding.ActivityHelpBinding
import com.aravindkarthik.blanca.lang.FunctionsProvider
import com.aravindkarthik.blanca.ui.canvas.CanvasView

class HelpActivity : AppCompatActivity() {
    lateinit var binding : ActivityHelpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.helpView)
        binding.helpView.setup(FunctionsProvider(CanvasView(this)).getFunctions())
    }

    companion object {
        fun launch(context : Context) {
            val intent = Intent(context, HelpActivity::class.java)
            context.startActivity(intent)
        }
    }
}