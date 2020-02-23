package com.aravindkarthik.blanca.lang.drawing

import com.aravindkarthik.blanca.CanvasView
import com.aravindkarthik.blanca.lang.core.DataTypes
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.IntType

class DrawLineFunction(private val canvasView: CanvasView) : Function() {
    override val name: String = "drawLine"
    override val functionArguments: List<DataTypes> = listOf(IntType, IntType, IntType, IntType)

    override fun invokeFunction(lineNumber: Int, arguments: List<String>) {
        canvasView.drawLine(
            arguments[0].toFloat(),
            arguments[1].toFloat(),
            arguments[2].toFloat(),
            arguments[3].toFloat()
        )
    }
}