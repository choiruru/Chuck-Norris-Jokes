package com.hdk24.basemvvm.ui.binding

import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.chucknorrisjokes.R


@BindingAdapter("lyt_color")
fun LinearLayout.bindLayoutColor(color:Int){
    val drawable: GradientDrawable = this.background as GradientDrawable
    val pallete:IntArray= context.resources.getIntArray(R.array.pallete)
    drawable.setColor(pallete[color])
}

@BindingAdapter("lyt_color")
fun CardView.bindLayoutColor(color:Int){
//    val drawable: GradientDrawable = this.background as GradientDrawable
    val pallete:IntArray= context.resources.getIntArray(R.array.pallete)
    this.setCardBackgroundColor(pallete[color])
//    drawable.setColor(pallete[color])
}

@BindingAdapter("lyt_color")
fun ConstraintLayout.bindLayoutColor(color:Int){
//    val drawable: GradientDrawable = this.background as GradientDrawable
    val pallete:IntArray= context.resources.getIntArray(R.array.pallete)
    this.setBackgroundColor(pallete[color])
//    drawable.setColor(pallete[color])
}