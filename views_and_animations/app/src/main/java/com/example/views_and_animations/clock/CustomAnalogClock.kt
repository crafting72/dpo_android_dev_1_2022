package com.example.views_and_animations.clock

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlinx.coroutines.*
import kotlin.math.cos
import kotlin.math.sin

class CustomAnalogClock @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    private var timeListeners = mutableSetOf<(TimeState) -> Unit>()
    private var timeState = TimeState(0, false)
        set(value) {
            if (value == field)
                return
            field = value
            timeListeners.forEach{ it(value) }
        }
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var job: Job

    /** height, width of the clock's view */
    private var mHeight = 0
    private var mWidth = 0

    /** numeric numbers to denote the hours */
    private val mClockHours = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

    /** numeric numbers to denote the hours */
    private var mPadding = 0
    private var mNumeralSpacing = 0

    /** truncation of the heights of the clock-hands,
    hour clock-hand will be smaller comparatively to others */
    private var mHandTruncation = 0
    private var mHourHandTruncation = 0

    /** others attributes to calculate the locations of hour-points */
    private var mRadius = 0
    private lateinit var mPaint: Paint
    private val mRect = Rect()
    private var isInit: Boolean = false

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        /** initialize necessary values */
        if (!isInit){
            mPaint = Paint()
            mHeight = height
            mWidth = width
            mPadding = mNumeralSpacing + 50 // spacing from the circle border
            val minAttr = mHeight.coerceAtMost(mWidth)
            mRadius = minAttr / 2 - mPadding

            // for maintaining different heights among the clock-hands
            mHandTruncation = minAttr / 20
            mHourHandTruncation = minAttr / 17

            isInit = true  // set true once initialized
        }

        canvas.drawColor(Color.DKGRAY)

        /** circle border */
        mPaint.reset()
        mPaint.color = Color.WHITE
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 4F
        mPaint.isAntiAlias = true
        canvas.drawCircle(mWidth / 2F, mHeight / 2F, mRadius + mPadding - 10F, mPaint)

        /** clock-center */
        mPaint.style = Paint.Style.FILL
        canvas.drawCircle(mWidth / 2F, mHeight / 2F, 12F, mPaint)
        // the 03 clock hands will be rotated from this center point.

        /** border of hours */

        val fontSize =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                14F,
                resources.displayMetrics
            )
        mPaint.textSize = fontSize  // set font size (optional)

        for (hour in mClockHours) {
            val tmp = hour.toString()
            mPaint.getTextBounds(tmp, 0, tmp.length, mRect) // for circle-wise bounding

            // find the circle-wise (x, y) position as mathematical rule
            val angle = Math.PI / 6 * (hour - 3)
            val x = mWidth / 2 + cos(angle) * mRadius - mRect.width() / 2
            val y = mHeight / 2 + sin(angle) * mRadius + mRect.height() / 2

            canvas.drawText(hour.toString(), x.toFloat(), y.toFloat(), mPaint)
        // you can draw dots to denote hours as alternative
        }

        /*  /** draw clock hands to represent the every single time */

          val calendar = Calendar.getInstance()
          var hour = calendar.get(Calendar.HOUR_OF_DAY)
          hour = if(hour > 12) hour - 12 else hour

          drawHandLine(
              canvas,
              (hour + calendar.get(Calendar.MINUTE) / 60.0) * 5,
              isHour = true,
              isSecond = false) // draw hours
          drawHandLine(
              canvas,
              calendar.get(Calendar.MINUTE).toDouble(),
              isHour = false,
              isSecond = false) // draw minutes
          drawHandLine(
              canvas,
              calendar.get(Calendar.SECOND).toDouble(),
              isHour = false,
              isSecond = true) // draw seconds

          /** invalidate the appearance for next representation of time  */
          postInvalidateDelayed(500)
          invalidate() */

        val time = currentTime()
        val hour = time / (1000 * 60 * 60) % 12
        val minute = time / (1000 * 60) % 60
        val second = time / 1000 % 60

        drawHandLine(
            canvas,
            (hour + minute / 60.0) * 5,
            isHour = true,
            isSecond = false) // draw hours
        drawHandLine(
            canvas,
            minute.toDouble(),
            isHour = false,
            isSecond = false) // draw minutes
        drawHandLine(
            canvas,
            second.toDouble(),
            isHour = false,
            isSecond = true) // draw seconds
    }

    private fun drawHandLine(canvas: Canvas, moment: Double, isHour: Boolean, isSecond: Boolean) {
        val angle = Math.PI * moment / 30 - Math.PI / 2
        val handRadius =
            if (isHour) mRadius - mHandTruncation - mHourHandTruncation
            else mRadius - mHandTruncation
        if (isSecond) mPaint.color = Color.YELLOW
        canvas.drawLine(
            mWidth / 2F,
            mHeight / 2F,
            (mWidth / 2 + cos(angle) * handRadius).toFloat(),
            (mHeight / 2 + sin(angle) * handRadius).toFloat(),
            mPaint
        )
    }

    fun start(){
        timeState = TimeState(timeState.time,true)
        job = mainScope.launch {
            while (timeState.isPlayed){
                delay(1000)
                timeState = TimeState(timeState.time + 1000 ,timeState.isPlayed)
                invalidate()
            }
        }
    }

    fun stop(){
        timeState = TimeState(timeState.time,false)
        job.cancel()
    }

    fun reset(){
        stop()
        timeState = TimeState(0,timeState.isPlayed)
        invalidate()
    }

    private fun currentTime() = timeState.time

    fun addUpdateListener(listener: (TimeState) -> Unit){
        timeListeners.add(listener)
        listener(timeState)
    }

    fun removeListener(listener: (TimeState) -> Unit){
        timeListeners.remove(listener)
    }
}