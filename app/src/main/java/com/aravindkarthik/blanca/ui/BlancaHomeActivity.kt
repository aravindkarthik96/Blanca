package com.aravindkarthik.blanca.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aravindkarthik.blanca.R
import com.aravindkarthik.blanca.help.HelpActivity
import com.aravindkarthik.blanca.lang.FunctionsProvider
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.parseParams
import com.aravindkarthik.blanca.lang.drawing.*
import com.aravindkarthik.blanca.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_blanca_home.*
import kotlinx.coroutines.*

class BlancaHomeActivity : AppCompatActivity() {

    private lateinit var functions: List<Function>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blanca_home)
        functions = FunctionsProvider(canvasView).getFunctions()

        setupSuggestions()
        setupHelp()
    }

    private fun setupHelp() {
        helpButton.setOnClickListener {
            HelpActivity.launch(this)
        }

        settingsButton.setOnClickListener {
            SettingsActivity.launch(this)
        }
    }

    private fun setupSuggestions() {
        funcSuggestions.init(codeEditor, functions)
    }

    fun handleEditorTitleClick(view: View) {
        loggerView.visibility = View.GONE
        editorContainer.visibility = View.VISIBLE
        funcSuggestions.visibility = View.VISIBLE
        loggerTitle.setTextColor(getColor(R.color.blanca_tabs_deselected_color))
        editorTitle.setTextColor(getColor(R.color.blanca_tabs_selected_color))
    }

    fun handleLoggerTitleClick(view: View) {
        loggerView.visibility = View.VISIBLE
        editorContainer.visibility = View.GONE
        funcSuggestions.visibility = View.INVISIBLE
        loggerTitle.setTextColor(getColor(R.color.blanca_tabs_selected_color))
        editorTitle.setTextColor(getColor(R.color.blanca_tabs_deselected_color))
    }

    fun runCode(view: View) {
        handleLoggerTitleClick(view)
        interpretCode()
    }


    private fun interpretCode() {
        val codeEditable = codeEditor.text

        codeEditable.lines().forEachIndexed { index, codeLine ->
            when {
                codeLine.startsWith("//") -> handleComments()
                codeLine.startsWith("$") -> handleVariables()
                else -> {
                    GlobalScope.launch(Dispatchers.IO) {
                        val beginTime = System.currentTimeMillis()
                        printLog("Execution started")

                        functions.forEach {
                            if (codeLine.startsWith(it.name)
                                && codeLine.endsWith(")")
                            ) {
                                val arguments = codeLine.parseParams()
                                if (it.validateArguments(arguments)) {
                                    runBlocking(Dispatchers.IO) {
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
    private suspend fun printError(index: Int, codeLine: String, message: String? = "UNKNOWN SYNTAX") {
        withContext(Dispatchers.Main) {
            val logText = "$message AT $index : $codeLine"
            Log.d("BLANCA INTERPRETER", logText)
            printMessage(logText)
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun printLog(message: String) {
        withContext(Dispatchers.Main) {
            Log.d("BLANCA INTERPRETER", message)
            printMessage(message)
        }
    }

    private fun printMessage(logText: String) {
        val currentText = loggerView.text.toString()
        loggerView.text = "$currentText \n-> $logText"
    }

    companion object {
        fun launch(context : Context) {
            val intent = Intent(context, BlancaHomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}
