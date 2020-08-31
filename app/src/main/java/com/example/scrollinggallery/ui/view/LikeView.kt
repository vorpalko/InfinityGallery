package com.example.scrollinggallery.ui.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.OvershootInterpolator
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import com.example.scrollinggallery.R

class LikeView(
    context: Context, attrs: AttributeSet
): RelativeLayout(context, attrs) {
    private var imageHeart: AppCompatImageView? = null

    init {
        imageHeart = AppCompatImageView(context)

        val likeSrc = R.drawable.ic_heart_white_80
        val likeSize = 580
        val heartParams = LayoutParams(likeSize, likeSize)
        heartParams.addRule(CENTER_IN_PARENT, TRUE)
        imageHeart!!.layoutParams = heartParams
        imageHeart!!.visibility = GONE
        imageHeart!!.setImageResource(likeSrc)
        addView(imageHeart)
    }

    fun start() {
        imageHeart!!.visibility = VISIBLE
        imageHeart!!.scaleY = 0f
        imageHeart!!.scaleX = 0f
        val animatorSet = AnimatorSet()
        val likeScaleUpYAnimator = ObjectAnimator.ofFloat(imageHeart, SCALE_Y, 0f, 1f)
        likeScaleUpYAnimator.duration = 400
        likeScaleUpYAnimator.interpolator = OvershootInterpolator()
        val likeScaleUpXAnimator = ObjectAnimator.ofFloat(imageHeart, SCALE_X, 0f, 1f)
        likeScaleUpXAnimator.duration = 400
        likeScaleUpXAnimator.interpolator = OvershootInterpolator()
        val likeScaleDownYAnimator = ObjectAnimator.ofFloat(imageHeart, SCALE_Y, 1f, 0f)
        likeScaleDownYAnimator.duration = 100
        val likeScaleDownXAnimator = ObjectAnimator.ofFloat(imageHeart, SCALE_X, 1f, 0f)
        likeScaleDownXAnimator.duration = 100
        animatorSet.playTogether(
            likeScaleUpXAnimator,
            likeScaleUpYAnimator
        )
        animatorSet.play(likeScaleDownXAnimator).with(likeScaleDownYAnimator).after(800)
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                imageHeart!!.visibility = GONE
            }
        })
        animatorSet.start()
    }
}