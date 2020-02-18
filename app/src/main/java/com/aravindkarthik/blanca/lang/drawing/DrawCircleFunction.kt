package com.aravindkarthik.blanca.lang.drawing

import com.aravindkarthik.blanca.CanvasView
import com.aravindkarthik.blanca.lang.core.DataTypes
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.IntType

class DrawCircleFunction(private val canvasView: CanvasView) : Function() {

    override val name: String = "drawCircle"
    override val functionArguments: List<DataTypes> = listOf(IntType, IntType, IntType)

    override fun invokeFunction(lineNumber: Int, arguments: List<String>) {
        canvasView.drawCircle(
            getIntArgument(arguments[0]),
            getIntArgument(arguments[1]),
            getIntArgument(arguments[2])
        )
    }
}