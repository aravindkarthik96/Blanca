package com.aravindkarthik.blanca.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.aravindkarthik.blanca.R

class BlancaSplashActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_blanca_splash)

        Handler().postDelayed({
            BlancaHomeActivity.launch(this)
            this.finish()
        }, 1000)
    }

}