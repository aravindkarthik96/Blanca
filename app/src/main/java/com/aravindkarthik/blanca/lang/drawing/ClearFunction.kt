package com.aravindkarthik.blanca.lang.drawing

import com.aravindkarthik.blanca.ui.canvas.CanvasView
import com.aravindkarthik.blanca.lang.core.DataTypes
import com.aravindkarthik.blanca.lang.core.Function
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
}