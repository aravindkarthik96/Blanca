package com.aravindkarthik.blanca

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.canvasView
import kotlinx.android.synthetic.main.activity_main.code_editor
import kotlinx.android.synthetic.main.activity_main.run_code

private const val MIN_OPENGL_VERSION = 3.0

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
                codeLine.startsWith("draw") -> handleDraw(index, codeLine)
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
            codeLine.startsWith("drawHLine(") && codeLine.endsWith(")") -> drawHorizontalLine(
                    codeLine.parseParams())
            codeLine.startsWith("drawVLine(") && codeLine.endsWith(")") -> drawVerticalLine(
                    codeLine)
            codeLine.startsWith("drawCircle(") && codeLine.endsWith(")") -> drawCircle(codeLine)
            else -> showError(index, codeLine)
        }
    }

    private fun drawVerticalLine(codeLine: String) {

    }

    private fun drawHorizontalLine(codeLine: List<String>?) {

    }

    private fun drawCircle(codeLine: String) {
        canvasView.drawCircle(20)
    }

    private fun drawLine(codeLine: String) {
        canvasView.drawLine(10, 20, 20, 20)
    }
}

