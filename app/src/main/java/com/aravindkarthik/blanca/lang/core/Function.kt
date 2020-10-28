package com.aravindkarthik.blanca.lang.core

import com.aravindkarthik.blanca.ui.canvas.CanvasView

abstract class Function {
    abstract val name: String
    abstract val functionArguments: List<DataTypes>
    abstract val documentation: FunctionDocumentation

    abstract suspend fun invokeFunction(lineNumber: Int, arguments: List<String>)

    fun validateArguments(arguments: List<String>): Boolean {
        if (functionArguments.isNullOrEmpty()) {
            return arguments.isEmpty()
        }

        if (functionArguments.size != arguments.size) {
            return false
        }

        try {
            functionArguments.forEachIndexed { index, functionArgument ->
                when (functionArgument) {
                    is IntType -> {
                        arguments[index].toInt()
                    }
                    is DoubleType -> {
                        arguments[index].toDouble()
                    }
                    is FloatType -> {
                        arguments[index].toFloat()
                    }
                    is BooleanType -> {
                        arguments[index].toBoolean()
                    }
                    is StringType -> {
                        arguments[index]
                    }
                }
            }
        } catch (e: Exception) {
            return false
        }

        return true
    }

    fun getIntArgument(argument: String): Int {
        return argument.toInt()
    }

    fun getSuggestionText(): String {
        return "$name()\n"
    }
}

class FunctionDocumentation(
    val title: String,
    val description: String,
    val exampleCode: String,
    val runExample: (canvas : CanvasView, exampleCode : String) -> Unit
)