package com.aravindkarthik.blanca.lang.drawing

import com.aravindkarthik.blanca.lang.core.*
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.ui.canvas.CanvasView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WriteTextFunction(private val canvasView: CanvasView) : Function() {
    override val name: String
        get() = "writeText"
    override val functionArguments: List<DataTypes>
        get() = listOf(StringType, IntType, IntType)

    override suspend fun invokeFunction(lineNumber: Int, arguments: List<String>) {
        withContext(Dispatchers.Main) {
            canvasView.writeText(
                arguments[0],
                arguments[1].toInt(),
                arguments[2].toInt()
            )
        }
    }

    override val documentation: FunctionDocumentation
        get() = FunctionDocumentation(
            title = "writeText(text, X pos, Y pos)",
            description = "displays text at the given coordinates",
            exampleCode = "writeText(hello world, 40,40)",
            runExample = { canvas, _ ->
                GlobalScope.launch(Dispatchers.IO) {
                    withContext(Dispatchers.Main) {
                        canvas.writeText("hello world", 40, 40)
                    }
                }
            }
        )
}