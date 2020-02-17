package com.aravindkarthik.blanca

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class CanvasView @JvmOverloads constructor(context: Context,
        attributeSet: AttributeSet? = null,
        defStyle: Int = 0) :
        View(context, attributeSet, defStyle) {

    private val paint = Paint()
    private var path = Path()

    init {
        paint.color = ContextCompat.getColor(context, R.color.blanca_text_green)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    fun clear() {
        invalidate()
    }

    fun drawLine(
            startX: Int,
            startY: Int,
            endX: Int,
            endY: Int
    ) {
        path.addRect(100f, 20f, 100f, 4f, Path.Direction.CW)
        invalidate()
    }

    fun drawCircle(radius: Int) {
        path.addCircle(100f, 100f, radius.toFloat(), Path.Direction.CW)
        invalidate()
    }

    fun drawCircle(radius: Int, xPos: Int, yPos: Int) {
        path.addCircle(xPos.toFloat(), yPos.toFloat(), radius.toFloat(), Path.Direction.CW)
        invalidate()
    }

    fun drawVerticalLine(x: Int, yStart: Int, yEnd: Int) {
        val distance = if (yStart > yEnd) {
            yStart - yEnd
        } else {
            yEnd - yStart
        }

        (0..distance).forEach { index ->
            drawCircle(10, x, index)
        }
    }

}