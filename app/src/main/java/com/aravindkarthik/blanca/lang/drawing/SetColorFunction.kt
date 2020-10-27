package com.aravindkarthik.blanca.lang.drawing

import com.aravindkarthik.blanca.ui.canvas.CanvasView
import com.aravindkarthik.blanca.lang.core.DataTypes
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.StringType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
}
