package com.aravindkarthik.blanca

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class CanvasView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) :
    View(context, attributeSet, defStyle) {

    private val paint = Paint()
    private val linePaint = Paint()
    private var path = Path()

    private var lines: MutableList<Line> = mutableListOf()

    init {
        paint.color = ContextCompat.getColor(context, R.color.blanca_text_green)
        linePaint.color = ContextCompat.getColor(context, R.color.blanca_text_green)
        linePaint.strokeWidth = 4f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
        lines.forEach { canvas.drawLine(it.startX, it.startY, it.endX, it.endY, linePaint) }
    }

    fun clear() {
        path = Path()
        lines = mutableListOf()
        invalidate()
    }


    fun drawLine(startX: Float, startY: Float, endX: Float, endY: Float) {
        lines.add(
            Line(
                startX,
                startY,
                endX,
                endY
            )
        )
    }

    fun drawCircle(radius: Int, xPos: Int, yPos: Int) {
        path.addCircle(xPos.toFloat(), yPos.toFloat(), radius.toFloat(), Path.Direction.CW)
        invalidate()
    }

}

private data class Line(
    val startX: Float,
    val startY: Float,
    val endX: Float,
    val endY: Float
)