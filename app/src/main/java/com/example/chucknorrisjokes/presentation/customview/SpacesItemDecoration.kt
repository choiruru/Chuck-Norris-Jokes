package com.example.chucknorrisjokes.presentation.customview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hdk24.basemvvm.utils.ScreenUtils

class SpacesItemDecoration : RecyclerView.ItemDecoration {

    private val top: Int
    private val right: Int
    private val bottom: Int
    private val left: Int

    constructor(space: Float) : super() {
        val spaces = ScreenUtils.dpToPx(space)
        this.top = spaces
        this.right = spaces
        this.bottom = spaces
        this.left = spaces
    }

    constructor(top: Float, right: Float, bottom: Float, left: Float) : super() {
        this.top = ScreenUtils.dpToPx(top)
        this.right = ScreenUtils.dpToPx(right)
        this.bottom = ScreenUtils.dpToPx(bottom)
        this.left = ScreenUtils.dpToPx(left)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = left
        outRect.right = right
        outRect.bottom = bottom

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = top
        }
    }
}