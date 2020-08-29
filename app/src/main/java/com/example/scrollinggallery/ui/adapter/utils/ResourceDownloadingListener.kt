package com.example.scrollinggallery.ui.adapter.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import android.widget.ToggleButton
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.example.scrollinggallery.ui.IMAGE_FADE_DURATION

class ResourceDownloadingListener(
            private val nameText: TextView,
            private val likeButton: ToggleButton
): RequestListener<Drawable> {

    override fun onResourceReady(resource: Drawable,
                                 model: Any,
                                 target: Target<Drawable>,
                                 dataSource: DataSource,
                                 isFirstResource: Boolean
    ): Boolean {
        nameText.visibility = View.VISIBLE
        likeButton.visibility = View.VISIBLE
        target.onResourceReady(resource, DrawableCrossFadeTransition(IMAGE_FADE_DURATION, isFirstResource))
        return true
    }

    override fun onLoadFailed(e: GlideException?,
                              model: Any,
                              target: Target<Drawable>,
                              isFirstResource: Boolean) = false
}