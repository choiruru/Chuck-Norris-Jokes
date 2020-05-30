package com.hdk24.basemvvm.ui.binding

import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.example.chucknorrisjokes.R


@BindingAdapter("lyt_color")
fun LinearLayout.bindLayoutColor(color:Int){
    val drawable: GradientDrawable = this.background as GradientDrawable
    val pallete:IntArray= context.resources.getIntArray(R.array.pallete)
    drawable.setColor(pallete[color])
}