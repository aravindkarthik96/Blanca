package com.aravindkarthik.blanca

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        run_code.setOnClickListener {
            interpretCode()
        }

    }

    private fun interpretCode() {
        val codeEditable = code_editor.text
        val codeString = codeEditable.toString()

        codeEditable.lines().forEachIndexed { index, codeLine ->
            when {
                codeLine.startsWith("drawLine") -> handleDraw(index,codeLine)
                codeLine.startsWith("//") -> handleComments()
                codeLine.startsWith("delay") -> handleDelay(codeLine, index)
                codeLine.startsWith("$") -> handleVariables()
                else -> showError(index, codeLine)
            }
        }

    }

    private fun handleVariables() {

    }

    private fun handleDelay(codeLine: String, index: Int) {
        when {
            codeLine.startsWith("delay(") && codeLine.endsWith(")") -> {
                Log.d("BLANCA INTERPRETER", "pausing for")
            }
            else -> showError(index, codeLine)
        }
    }

    private fun handleComments() {
        Log.d("BLANCA INTERPRETER", "ignoring comment")
    }

    private fun showError(index: Int, codeLine: String) {
        Log.d("BLANCA INTERPRETER", "UNKNOWN SYNTAX AT $index : $codeLine")
    }

    private fun handleDraw(index: Int, codeLine: String) {
        Log.d("BLANCA INTERPRETER", "Draw handler: drawing")
        when {
            codeLine.startsWith("drawLine(") && codeLine.endsWith(")") -> drawLine(codeLine)
            else -> showError(index, codeLine)
        }
    }

    private fun drawLine(codeLine: String) {
        canvasView.drawLine()
    }
}
