package com.example.scrollinggallery.ui.adapter.utils

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ToggleButton

class DoubleTapListener(
                private val likeButton: ToggleButton
): GestureDetector.SimpleOnGestureListener() {

    override fun onDown(e: MotionEvent) = true

    override fun onDoubleTap(e: MotionEvent): Boolean {
        if(likeButton.visibility == View.VISIBLE){
            if(!likeButton.isChecked){
                likeButton.toggle()
            }
        }
        return true
    }
}