package com.example.scrollinggallery.ui.adapter

import android.view.View
import android.widget.TextView
import com.example.scrollinggallery.ui.view.CircleProgressBar
import me.jessyan.progressmanager.ProgressListener
import me.jessyan.progressmanager.body.ProgressInfo
import timber.log.Timber

class GlideListenerProvider {

    fun getGlideListener(
        progressBar: CircleProgressBar,
        name: TextView
        //buttonLike: ImageView
    ): ProgressListener {
        return object : ProgressListener {
            override fun onProgress(progressInfo: ProgressInfo) {
                val progress = progressInfo.percent.toFloat()
                progressBar.setProgress(progress)

                if (progressInfo.isFinish){
                    name.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
            override fun onError(id: Long, e: Exception?) { Timber.e("Glide Progress Error")}
        }
    }
}