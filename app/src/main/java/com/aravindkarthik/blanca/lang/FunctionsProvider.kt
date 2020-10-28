package com.aravindkarthik.blanca.lang

import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.drawing.*
import com.aravindkarthik.blanca.ui.canvas.CanvasView

class FunctionsProvider(private val canvasView: CanvasView) {
    private val functions = mutableListOf<Function>()

    init {
        setup()
    }

    private fun setup() {
        functions.add(DelayFunction())
        functions.add(ClearFunction(canvasView))
        functions.add(DrawLineFunction(canvasView))
        functions.add(WriteTextFunction(canvasView))
        functions.add(DrawCircleFunction(canvasView))
        functions.add(SetColorFunction(canvasView))
    }

    fun getFunctions(): List<Function> {
        return functions
    }

}