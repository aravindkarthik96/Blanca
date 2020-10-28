package com.aravindkarthik.blanca.lang.drawing

import com.aravindkarthik.blanca.ui.canvas.CanvasView
import com.aravindkarthik.blanca.lang.core.DataTypes
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.FunctionDocumentation
import com.aravindkarthik.blanca.lang.core.IntType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DrawLineFunction(private val canvasView: CanvasView) : Function() {
    override val name: String = "drawLine"
    override val functionArguments: List<DataTypes> = listOf(IntType, IntType, IntType, IntType)

    override suspend fun invokeFunction(lineNumber: Int, arguments: List<String>) {
        withContext(Dispatchers.Main) {
            canvasView.drawLine(
                arguments[0].toFloat(),
                arguments[1].toFloat(),
                arguments[2].toFloat(),
                arguments[3].toFloat()
            )
        }
    }

    override val documentation: FunctionDocumentation
        get() = FunctionDocumentation(
            title = "drawLine(X1 pos, Y1 pos, X2 pos, Y2 pos)",
            description = "draws a straight line from point a to point b",
            exampleCode = "drawLine(10,10,60,60)",
            runExample = { canvas, _ ->
                GlobalScope.launch(Dispatchers.IO) {
                    withContext(Dispatchers.Main) { canvas.drawLine(10f, 10f, 60f,60f) }
                }
            }
        )
}