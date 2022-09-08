package com.aravindkarthik.blanca.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aravindkarthik.blanca.R
import com.aravindkarthik.blanca.databinding.ActivityBlancaHomeBinding
import com.aravindkarthik.blanca.help.HelpActivity
import com.aravindkarthik.blanca.lang.FunctionsProvider
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.parseParams
import com.aravindkarthik.blanca.settings.SettingsActivity
import kotlinx.coroutines.*

class BlancaHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlancaHomeBinding
    private lateinit var functions: List<Function>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBlancaHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        functions = FunctionsProvider(binding.canvasView).getFunctions()

        setupSuggestions()
        setupHelp()
    }

    private fun setupHelp() {
        binding.helpButton.setOnClickListener {
            HelpActivity.launch(this)
        }

        binding.settingsButton.setOnClickListener {
            SettingsActivity.launch(this)
        }
    }

    private fun setupSuggestions() {
        binding.funcSuggestions.init(binding.codeEditor, functions)
    }

    fun handleEditorTitleClick(view: View) {
        binding.loggerView.visibility = View.GONE
        binding.editorContainer.visibility = View.VISIBLE
        binding.funcSuggestions.visibility = View.VISIBLE
        binding.loggerTitle.setTextColor(getColor(R.color.blanca_tabs_deselected_color))
        binding.editorTitle.setTextColor(getColor(R.color.blanca_tabs_selected_color))
    }

    fun handleLoggerTitleClick(view: View) {
        binding.loggerView.visibility = View.VISIBLE
        binding.editorContainer.visibility = View.GONE
        binding.funcSuggestions.visibility = View.INVISIBLE
        binding.loggerTitle.setTextColor(getColor(R.color.blanca_tabs_selected_color))
        binding.editorTitle.setTextColor(getColor(R.color.blanca_tabs_deselected_color))
    }

    fun runCode(view: View) {
        handleLoggerTitleClick(view)
        interpretCode()
    }


    private fun interpretCode() {
        val codeEditable = binding.codeEditor.text

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
        val currentText = binding.loggerView.text.toString()
        binding.loggerView.text = "$currentText \n-> $logText"
    }

    companion object {
        fun launch(context : Context) {
            val intent = Intent(context, BlancaHomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}
