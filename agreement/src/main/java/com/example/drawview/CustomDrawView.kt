package com.example.drawview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.example.agreement.R

class CustomDrawView constructor(
    context: Context,
    attributeSet: AttributeSet) : AppCompatImageView(context, attributeSet)
{
    private var mDefaultColor        = 0
    private var mLatestPath: Path?   = null
    private var mLatestPaint: Paint? = null

    private val paintPenList= mutableListOf<Paint>()
    private val pathPenList = mutableListOf<Path>()

    init {
        mDefaultColor = ContextCompat.getColor(context, R.color.black)

        initPaintNPen()
    }

    private fun initPaintNPen() {
        mLatestPaint = getNewPaintPen()
        mLatestPath  = getNewPathPen()

        mLatestPaint?.let { paintPenList.add(it) }
        mLatestPath?.let { pathPenList.add(it) }
    }

    private fun getNewPathPen(): Path {
        return Path()
    }

    private fun getNewPaintPen(): Paint {
        val mPaintPen = Paint()

        mPaintPen.strokeWidth = LINE_WIDTH
        mPaintPen.isAntiAlias = true
        mPaintPen.isDither    = true
        mPaintPen.style       = Paint.Style.STROKE
        mPaintPen.strokeJoin  = Paint.Join.MITER
        mPaintPen.strokeCap   = Paint.Cap.ROUND
        mPaintPen.color       = mDefaultColor

        return mPaintPen
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startPath(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                updatePath(x, y)
            }
        }

        invalidate()
        return true
    }

    private fun startPath(x: Float, y: Float) {
        initPaintNPen()
        mLatestPath?.moveTo(x, y)
    }

    private fun updatePath(x: Float, y: Float) {
        mLatestPath?.lineTo(x, y)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (i in 0 until paintPenList.size) {
            canvas.drawPath(pathPenList[i], paintPenList[i])
        }
    }

    fun resetView() {
        mLatestPath?.reset()
        mLatestPaint?.reset()

        pathPenList.clear()
        paintPenList.clear()

        initPaintNPen()
        invalidate()
    }

    companion object {

        private const val LINE_WIDTH   = 10f


    }


}