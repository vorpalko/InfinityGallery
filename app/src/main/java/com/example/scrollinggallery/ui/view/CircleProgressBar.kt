package com.example.scrollinggallery.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.scrollinggallery.R
import com.example.scrollinggallery.ui.PROGRESS_BAR_BOTTOM_LAYER_ALPHA
import com.example.scrollinggallery.ui.PROGRESS_BAR_MAX_PROGRESS
import com.example.scrollinggallery.ui.PROGRESS_BAR_THICKNESS
import kotlin.math.roundToInt

class CircleProgressBar(
              context: Context,
              attrs: AttributeSet
) : View(context, attrs) {

    private var progress = 0f

    private var progressColor = Color.DKGRAY
    private val bottomLayerAlpha = PROGRESS_BAR_BOTTOM_LAYER_ALPHA
    private val max = PROGRESS_BAR_MAX_PROGRESS
    private val thickness = PROGRESS_BAR_THICKNESS

    private var rectF = RectF()
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val foregroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        initColor(attrs)
        initPaint(foregroundPaint, false)
        initPaint(backgroundPaint, true)
    }

    fun setProgress(progress: Float){
        this.progress = progress
        invalidate()
    }

    private fun setAlphaBottomLayer(color: Int, modifier: Float): Int {
        val alpha = (Color.alpha(color) * modifier).roundToInt()
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }

    private fun initColor(attrs: AttributeSet){
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CircleProgressBar,
            0, 0
        )
        progressColor = typedArray.getInt(R.styleable.CircleProgressBar_progressbarColor, progressColor)
    }

    private fun initPaint(paint: Paint, isBackground: Boolean){
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = thickness
        paint.color =
            if (isBackground)
                setAlphaBottomLayer(progressColor, bottomLayerAlpha)
            else
                progressColor
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        canvas.drawOval(rectF, backgroundPaint)
        val angle = 360 * progress / max
        canvas.drawArc( rectF, -90f, angle, false, foregroundPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int){
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val min = width.coerceAtMost(height)
        setMeasuredDimension(min, min)
        rectF[thickness / 2, thickness / 2, min - thickness / 2] =
            min - thickness / 2
    }
}