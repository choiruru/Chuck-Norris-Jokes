package com.example.chucknorrisjokes.presentation.customview

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.example.chucknorrisjokes.R

class RoundLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var drawable: GradientDrawable
    private var topL:Float
    private var topR:Float
    private var bottomL:Float
    private var bottomR:Float

    init {
        attrs.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.RoundLinearLayout, 0, 0)
            topL = typedArray.getDimension(R.styleable.RoundLinearLayout_round_top_left,0F)
            topR = typedArray.getDimension(R.styleable.RoundLinearLayout_round_top_right,0F)
            bottomL = typedArray.getDimension(R.styleable.RoundLinearLayout_round_bottom_left,0F)
            bottomR = typedArray.getDimension(R.styleable.RoundLinearLayout_round_bottom_right,0F)

            drawable = this.background as GradientDrawable
            updateDrawable()

            typedArray.recycle()
        }
    }

    fun setRound_top_right(value: Float){
        topR = value
        updateDrawable()
    }

    fun setRound_top_left(value: Float){
        topL = value
        updateDrawable()
    }

    private fun updateDrawable(){
        drawable.cornerRadii = floatArrayOf(topL,topL,topR,topR,bottomR,bottomR,bottomL,bottomL)
    }
}