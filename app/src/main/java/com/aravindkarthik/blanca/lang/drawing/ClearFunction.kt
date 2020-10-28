package com.aravindkarthik.blanca.lang.drawing

import com.aravindkarthik.blanca.lang.core.DataTypes
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.FunctionDocumentation
import com.aravindkarthik.blanca.ui.canvas.CanvasView
import kotlinx.coroutines.*

class ClearFunction(private val canvasView: CanvasView) : Function() {

    override val name: String
        get() = "clear"
    override val functionArguments: List<DataTypes>
        get() = emptyList()

    override suspend fun invokeFunction(lineNumber: Int, arguments: List<String>) {
        withContext(Dispatchers.Main) {
            canvasView.clear()
        }
    }

    override val documentation: FunctionDocumentation
        get() = FunctionDocumentation(
            title = "clear()",
            description = "clears everything drawn on the canvas",
            exampleCode = "drawCircle(200,200,200)\ndelay(1000)\nclear()",
            runExample = { canvas, _ ->
                GlobalScope.launch(Dispatchers.IO) {
                    withContext(Dispatchers.Main) { canvas.drawCircle(200, 200, 200) }
                    delay(1000)
                    withContext(Dispatchers.Main) { canvas.clear() }

                }
            }
        )
}