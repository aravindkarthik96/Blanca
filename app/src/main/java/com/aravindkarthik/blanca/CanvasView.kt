package com.aravindkarthik.blanca

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.PorterDuff



class CanvasView @JvmOverloads constructor(context: Context,
                                           attributeSet: AttributeSet? = null,
                                           defStyle: Int = 0) :
    View(context, attributeSet, defStyle) {

    private val paint = Paint()
    private val bitmapPaint = Paint(Paint.DITHER_FLAG);
    private var bitmap : Bitmap? = null
    private var rootCanvas : Canvas? = null
    private var path = Path()

    init {
        paint.color = context.resources.getColor(R.color.blanca_text_green)
        paint.isDither = true
        paint.isAntiAlias = true
        paint.strokeWidth = 50f
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SCREEN)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        }

        rootCanvas = Canvas(bitmap!!)
        rootCanvas?.drawColor(Color.TRANSPARENT)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap!!, 0f, 0f, bitmapPaint)
        canvas.drawPath(path, paint)
    }

    fun clear() {
        rootCanvas?.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        invalidate()
    }

    fun drawLine() {
        path.lineTo(10f, 100f)
        rootCanvas?.drawPath(path,paint)
        invalidate()
    }


}