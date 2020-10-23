package com.aravindkarthik.blanca

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.parseParams
import com.aravindkarthik.blanca.lang.drawing.ClearFunction
import com.aravindkarthik.blanca.lang.drawing.DrawCircleFunction
import com.aravindkarthik.blanca.lang.drawing.DrawLineFunction
import com.aravindkarthik.blanca.lang.drawing.WriteTextFunction
import kotlinx.android.synthetic.main.activity_blanca_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BlancaHomeActivity : AppCompatActivity() {

    private val functions = mutableListOf<Function>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blanca_home)

//        functions.add(DelayFunction())
        functions.add(ClearFunction(canvasView))
        functions.add(DrawLineFunction(canvasView))
        functions.add(WriteTextFunction(canvasView))
        functions.add(DrawCircleFunction(canvasView))
    }

    fun handleEditorTitleClick(view: View) {
        loggerView.visibility = View.GONE
        editorContainer.visibility = View.VISIBLE
        loggerTitle.setTextColor(getColor(R.color.blanca_text_de_selected))
        editorTitle.setTextColor(getColor(R.color.blanca_text_selected))
    }

    fun handleLoggerTitleClick(view: View) {
        loggerView.visibility = View.VISIBLE
        editorContainer.visibility = View.GONE
        loggerTitle.setTextColor(getColor(R.color.blanca_text_selected))
        editorTitle.setTextColor(getColor(R.color.blanca_text_de_selected))
    }

    fun runCode(view: View) {
        interpretCode()
    }


    private fun interpretCode() {
        val codeEditable = codeEditor.text

        codeEditable.lines().forEachIndexed { index, codeLine ->
            when {
                codeLine.startsWith("//") -> handleComments()
                codeLine.startsWith("$") -> handleVariables()
                else -> {
                    GlobalScope.launch(Dispatchers.Main) {
                        val beginTime = System.currentTimeMillis()
                        printLog("Execution started")

                        functions.forEach {
                            if (codeLine.startsWith(it.name)
                                && codeLine.endsWith(")")
                            ) {
                                val arguments = codeLine.parseParams()
                                if (it.validateArguments(arguments)) {
                                    runBlocking {
                                        it.invokeFunction(
                                            index,
                                            arguments
                                        )
                                    }
                                    val executionTime = System.currentTimeMillis() - beginTime
                                    printLog("Executed ${it.name} $executionTime ms")
                                } else {
                                    printError(index, codeLine, "INVALID ARGUMENTS")
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private fun handleVariables() {

    }

    private fun handleComments() {
        Log.d("BLANCA INTERPRETER", "ignoring comment")
    }

    @SuppressLint("SetTextI18n")
    private fun printError(index: Int, codeLine: String, message: String? = "UNKNOWN SYNTAX") {
        val logText = "$message AT $index : $codeLine"
        Log.d("BLANCA INTERPRETER", logText)
        printMessage(logText)
    }

    @SuppressLint("SetTextI18n")
    private fun printLog(message: String) {
        Log.d("BLANCA INTERPRETER", message)
        printMessage(message)
    }

    private fun printMessage(logText: String) {
        val currentText = loggerView.text.toString()
        loggerView.text = "$currentText \n-> $logText"
    }

}
