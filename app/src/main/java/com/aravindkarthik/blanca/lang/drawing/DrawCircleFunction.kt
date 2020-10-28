package com.aravindkarthik.blanca.lang.drawing

import com.aravindkarthik.blanca.ui.canvas.CanvasView
import com.aravindkarthik.blanca.lang.core.DataTypes
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.FunctionDocumentation
import com.aravindkarthik.blanca.lang.core.IntType
import kotlinx.coroutines.*

class DrawCircleFunction(private val canvasView: CanvasView) : Function() {

    override val name: String = "drawCircle"
    override val functionArguments: List<DataTypes> = listOf(IntType, IntType, IntType)

    override suspend fun invokeFunction(lineNumber: Int, arguments: List<String>) {
        withContext(Dispatchers.Main) {
            canvasView.drawCircle(
                getIntArgument(arguments[0]),
                getIntArgument(arguments[1]),
                getIntArgument(arguments[2])
            )
        }
    }

    override val documentation: FunctionDocumentation
        get() = FunctionDocumentation(
            title = "drawCircle(radius, x position, y position)",
            description = "draws a circle on the canvas",
            exampleCode = "drawCircle(200,200,200)",
            runExample = { canvas, _ ->
                GlobalScope.launch(Dispatchers.IO) {
                    withContext(Dispatchers.Main) { canvas.drawCircle(200, 200, 200) }
                }
            }
        )

}