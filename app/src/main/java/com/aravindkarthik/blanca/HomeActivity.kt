package com.aravindkarthik.blanca

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.parseParams
import com.aravindkarthik.blanca.lang.drawing.DrawCircleFunction
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        run_code.setOnClickListener {
//            canvasView.clear()
//            interpretCode()
//        }

    }
}

