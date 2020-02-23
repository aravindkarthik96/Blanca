package com.aravindkarthik.blanca

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.parseParams
import com.aravindkarthik.blanca.lang.drawing.DrawCircleFunction
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    private val functions = mutableListOf<Function>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        run_code.setOnClickListener {
            canvasView.clear()
            interpretCode()
        }

        functions.add(DrawCircleFunction(canvasView))
    }

    private fun interpretCode() {
        val codeEditable = code_editor.text

        codeEditable.lines().forEachIndexed { index, codeLine ->
            when {
                codeLine.startsWith("//") -> handleComments()
                codeLine.startsWith("delay") -> handleDelay(codeLine, index)
                codeLine.startsWith("$") -> handleVariables()
                else -> {
                    functions.forEach {
                        if (codeLine.startsWith(it.name)
                            && codeLine.endsWith(")")
                        ) {
                            val arguments = codeLine.parseParams()
                            if (it.validateArguments(arguments)) {
                                it.invokeFunction(index,arguments!!)
                            } else {
                                showError(index, codeLine, "INVALID ARGUMENTS")
                            }
                        }
                    }
                }
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

    private fun showError(index: Int, codeLine: String, message: String? = "UNKNOWN SYNTAX") {
        Log.d("BLANCA INTERPRETER", "$message AT $index : $codeLine")
    }

    private fun handleDraw(index: Int, codeLine: String) {
        Log.d("BLANCA INTERPRETER", "Draw handler: drawing")
        when {
            codeLine.startsWith("drawLine(") && codeLine.endsWith(")") -> drawLine(codeLine, index)
            codeLine.startsWith("drawHLine(") && codeLine.endsWith(")") -> drawHorizontalLine(
                codeLine, index
            )
            codeLine.startsWith("drawVLine(") && codeLine.endsWith(")") -> drawVerticalLine(
                codeLine, index
            )
            codeLine.startsWith("drawCircle(") && codeLine.endsWith(")") -> drawCircle(
                codeLine,
                index
            )
            else -> showError(index, codeLine)
        }
    }

    private fun drawVerticalLine(codeLine: String, index: Int) {

    }

    private fun drawHorizontalLine(codeLine: String, index: Int) {

    }

    private fun drawCircle(codeLine: String, index: Int) {
        val params = codeLine.parseParams()

        if (params?.size == 3) {
            try {
                canvasView.drawCircle(params[0].toInt(), params[1].toInt(), params[2].toInt())
            } catch (exception: Exception) {
                showError(
                    index,
                    codeLine,
                    "INVALID METHOD PARAMETERS DATA TYPES FOR drawCircle(int, int, int)"
                )
            }
        } else {
            showError(index, codeLine, "INVALID PARAMETERS COUNT, EXPECTED 3 FOUND ${params?.size}")
        }
    }

    private fun drawLine(codeLine: String, index: Int) {
        canvasView.drawLine(10, 20, 20, 20)
    }
}

