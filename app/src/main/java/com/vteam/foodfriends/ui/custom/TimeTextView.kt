package com.vteam.foodfriends.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.vteam.foodfriends.R

/**
 * Created by H2PhySicS on 12/13/2017.
 */
class TimeTextView : AppCompatTextView {
    private var isChosen : Boolean = false
    private lateinit var paint : Paint
    private lateinit var textPaint : Paint

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet){
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr : Int) : super(context, attributeSet, defStyleAttr){
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val xPos : Float = (width - textPaint.textSize * Math.abs(this.length() / 2)) / 2
        val yPos : Int = (height / 2 - (textPaint.ascent() + textPaint.descent()) / 2).toInt()
        val content : String = text as String
        val offset = width / 10
        if (isChosen){
            canvas?.drawRect(offset.toFloat(), -offset.toFloat(), (width - offset).toFloat(), (height + offset).toFloat(), paint)
            canvas?.drawText(content, xPos, yPos.toFloat(), textPaint)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setup()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        setup()
    }

    fun setSelectedItem(){
        isChosen = true
        setup()
    }

    fun removeSelectedItem(){
        isChosen = false
        setup()
    }

    private fun init(){
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    }

    private fun setup(){
        if (width == 0 && height == 0) return

        paint.style = Paint.Style.FILL
        paint.color = resources.getColor(R.color.bottom_plus_icon)
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = 70f
        textPaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        textPaint.color = resources.getColor(R.color.white)

        invalidate()
    }


}