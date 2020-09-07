package com.example.scrollinggallery.ui.adapter.list_utils

import android.view.View
import android.widget.TextView
import android.widget.ToggleButton
import com.example.scrollinggallery.ui.view.CircleProgressBar
import me.jessyan.progressmanager.ProgressListener
import me.jessyan.progressmanager.body.ProgressInfo
import timber.log.Timber
import java.lang.Exception

class ImageDownloadProgressListener(
            private val progressBar: CircleProgressBar,
            private val nameText: TextView,
            private val likeButton: ToggleButton
): ProgressListener {

    override fun onProgress(progressInfo: ProgressInfo) {
        if (progressInfo.isFinish){
            nameText.visibility = View.VISIBLE
            likeButton.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
        else{
            progressBar.visibility = View.VISIBLE
            val progress = progressInfo.percent.toFloat()
            progressBar.setProgress(progress)
        }
    }

    override fun onError(id: Long, e: Exception?) { Timber.e("Glide Progress Error") }
}