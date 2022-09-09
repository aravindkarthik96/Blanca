package com.aravindkarthik.blanca.settings

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aravindkarthik.blanca.databinding.ActivitySettingsBinding
import com.aravindkarthik.blanca.lang.FunctionsProvider
import com.aravindkarthik.blanca.ui.canvas.CanvasView

class SettingsActivity : AppCompatActivity() {
    lateinit var binding : ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.settingsView.setup(FunctionsProvider(CanvasView(this)).getFunctions())
    }

    companion object {
        fun launch(context : Context) {
            val intent = Intent(context, SettingsActivity::class.java)
            context.startActivity(intent)
        }
    }
}