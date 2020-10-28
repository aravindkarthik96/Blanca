package com.aravindkarthik.blanca.ui.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import androidx.core.content.ContextCompat
import com.aravindkarthik.blanca.R


class CanvasView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) :
    View(context, attributeSet, defStyle) {

    private var paint = Paint()
    private val linePaint = Paint()
    private val textPaint = Paint()

    private var path = Path()

    private var lines: MutableList<Line> = mutableListOf()
    private var text: MutableList<Text> = mutableListOf()

    init {
        setPaintColors(
            ContextCompat.getColor(context, R.color.blanca_text_green)
        )
    }

    private fun setPaintColors(color: Int) {
        paint.color = color

        linePaint.color = color
        linePaint.strokeWidth = convertDpToPixel(4f, context)

        textPaint.color = color
        textPaint.textSize = convertDpToPixel(14f, context)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
        lines.forEach { canvas.drawLine(it.startX, it.startY, it.endX, it.endY, linePaint) }
        text.forEach { canvas.drawText(it.text, it.x, it.y, textPaint) }
    }

    fun clear() {
        path = Path()
        lines = mutableListOf()
        text = mutableListOf()
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
        invalidate()
    }

    fun drawCircle(radius: Int, xPos: Int, yPos: Int) {
        path.addCircle(xPos.toFloat(), yPos.toFloat(), radius.toFloat(), Path.Direction.CW)
        invalidate()
    }

    fun writeText(string: String, x: Int, y: Int) {
        text.add(Text(string, x.toFloat(), y.toFloat()))
        invalidate()
    }

    fun setColor(colorString: String) {
        setPaintColors(Color.parseColor(ColorUtils.getColorHex(colorString)))
        invalidate()
    }

}

private data class Line(
    val startX: Float,
    val startY: Float,
    val endX: Float,
    val endY: Float
)

private data class Text(
    val text: String,
    val x: Float,
    val y: Float
)


fun convertDpToPixel(dp: Float, context: Context): Float {
    return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun convertPixelsToDp(px: Float, context: Context): Float {
    return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}