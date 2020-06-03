package com.example.chucknorrisjokes.ui.binding

import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.chucknorrisjokes.R


@BindingAdapter("lyt_color")
fun CardView.bindLayoutColor(color:Int){
    val pallete:IntArray= context.resources.getIntArray(R.array.pallete)
    this.setCardBackgroundColor(pallete[color])
}