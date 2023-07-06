package com.example.finalskillcinema.ui.search.yearpicker

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.finalskillcinema.R

import java.util.Calendar

typealias onDateClickListener = (data: Int) -> Unit

class YearPicker @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : View(context, attrs) {

    private val setting = SettingsPicker()

    var setDateListener: onDateClickListener? = null

    init {
        val startYear = Calendar.getInstance().get(Calendar.YEAR) - 12
        attrs?.let { setAttribute(it, startYear) }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setting.setupField(w, h)
        Draw.createMatrix(setting)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Draw.drawArrow(canvas, setting)
        Draw.drawBorder(canvas, setting)
        Draw.drawMatrixDate(canvas, setting)
        Draw.drawRangeData(canvas, setting)
    }

    @SuppressLint("Recycle")
    private fun setAttribute(attrs: AttributeSet, startYear: Int) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.YearPicker)
        setting.borderWidth = typeArray.getDimension(
            R.styleable.YearPicker_picker_border_width, context.dpToPx(DEFAULT_BORDER_WIDTH)
        )
        setting.borderColor = typeArray.getColor(
            R.styleable.YearPicker_picker_border_color, DEFAULT_BORDER_COLOR
        )
        setting.radius = typeArray.getDimension(
            R.styleable.YearPicker_picker_border_corner_radius,
            context.dpToPx(10)
        )
        setting.startDate = typeArray.getInt(R.styleable.YearPicker_picker_start_date, startYear)
        setting.textSizeSP =
            typeArray.getDimension(R.styleable.YearPicker_picker_text_size, context.dpToSp(15))

        typeArray.recycle()
        setting.paint.apply {
            color = setting.borderColor
            strokeWidth = setting.borderWidth
            style = Paint.Style.STROKE
        }
        setting.textPaint.apply {
            color = setting.textColor
            style = Paint.Style.FILL
            textSize = setting.textSizeSP
        }
    }


    private val listener = TouchListener()

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        listener.onTouchEvent(event, setting, setDateListener) { invalidate() }
        return super.onTouchEvent(event)
    }

    companion object {
        const val DEFAULT_BORDER_WIDTH = 2
        const val DEFAULT_BORDER_COLOR = Color.CYAN
        const val DEFAULT_COLUMN = 3
        const val DEFAULT_ROW = 5

    }
}