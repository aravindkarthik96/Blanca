package com.aravindkarthik.blanca.lang.drawing

import com.aravindkarthik.blanca.lang.core.DataTypes
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.FunctionDocumentation
import com.aravindkarthik.blanca.lang.core.StringType
import com.aravindkarthik.blanca.ui.canvas.CanvasView
import kotlinx.coroutines.*

class SetColorFunction(private val canvasView: CanvasView) : Function() {
    override val name: String
        get() = "setColor"

    override val functionArguments: List<DataTypes>
        get() = listOf(StringType)

    override suspend fun invokeFunction(lineNumber: Int, arguments: List<String>) {
        withContext(Dispatchers.Main) {
            canvasView.setColor(arguments.first())
        }
    }

    override val documentation: FunctionDocumentation
        get() = FunctionDocumentation(
            title = "setColor(color name)",
            description = "sets a color to the canvas",
            exampleCode = "setColor(green)\ndrawLine(10,10,60,60)\ndelay(1000)\nsetColor(red)",
            runExample = { canvas, _ ->
                GlobalScope.launch(Dispatchers.IO) {
                    withContext(Dispatchers.Main) {
                        canvas.clear()
                        canvas.setColor("green")
                        canvas.drawLine(10f, 10f, 60f, 60f)
                    }
                    runBlocking { delay(1000) }
                    withContext(Dispatchers.Main) {
                        canvas.setColor("red")
                    }
                }
            }
        )
}
