package com.aravindkarthik.blanca.lang.drawing

import com.aravindkarthik.blanca.CanvasView
import com.aravindkarthik.blanca.lang.core.DataTypes
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.IntType
import com.aravindkarthik.blanca.lang.core.StringType

class WriteTextFunction(private val canvasView: CanvasView) : Function() {
    override val name: String
        get() = "writeText"
    override val functionArguments: List<DataTypes>
        get() = listOf(StringType, IntType, IntType)

    override suspend fun invokeFunction(lineNumber: Int, arguments: List<String>) {
        canvasView.writeText(
            arguments[0],
            arguments[1].toInt(),
            arguments[2].toInt()
        )
    }
}