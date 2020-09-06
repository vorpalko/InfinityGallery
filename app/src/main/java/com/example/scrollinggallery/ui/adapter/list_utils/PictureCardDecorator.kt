package com.example.scrollinggallery.ui.adapter.list_utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollinggallery.R

class PictureCardDecorator: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State){
        super.getItemOffsets(outRect, view, parent, state)

        val pixelOffset = view.context.resources.getDimensionPixelOffset(R.dimen.card_margin)

        outRect.left = pixelOffset
        outRect.right = pixelOffset
        outRect.top = pixelOffset / 2
        outRect.bottom = pixelOffset / 2
    }
}