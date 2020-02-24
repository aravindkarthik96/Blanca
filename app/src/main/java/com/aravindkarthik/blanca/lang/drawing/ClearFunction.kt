package com.aravindkarthik.blanca.lang.drawing

import com.aravindkarthik.blanca.CanvasView
import com.aravindkarthik.blanca.lang.core.DataTypes
import com.aravindkarthik.blanca.lang.core.Function

class ClearFunction(private val canvasView: CanvasView) : Function() {

    override val name: String
        get() = "clear"
    override val functionArguments: List<DataTypes>
        get() = emptyList()

    override suspend fun invokeFunction(lineNumber: Int, arguments: List<String>) {
        canvasView.clear()
    }
}