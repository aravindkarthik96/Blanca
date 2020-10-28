package com.aravindkarthik.blanca.help

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aravindkarthik.blanca.R
import com.aravindkarthik.blanca.lang.FunctionsProvider
import com.aravindkarthik.blanca.ui.BlancaHomeActivity
import com.aravindkarthik.blanca.ui.canvas.CanvasView
import kotlinx.android.synthetic.main.activity_help.*

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        helpView.setup(FunctionsProvider(CanvasView(this)).getFunctions())
    }

    companion object {
        fun launch(context : Context) {
            val intent = Intent(context, HelpActivity::class.java)
            context.startActivity(intent)
        }
    }
}