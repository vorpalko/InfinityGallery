package com.example.scrollinggallery.ui.adapter

import android.annotation.SuppressLint
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scrollinggallery.R
import com.example.scrollinggallery.data.model.ResponseDTO
import com.example.scrollinggallery.domain.Pic
import kotlinx.android.synthetic.main.list_item_picture.view.*
import me.jessyan.progressmanager.ProgressManager
import com.example.scrollinggallery.ui.adapter.utils.DoubleTapListener
import com.example.scrollinggallery.ui.adapter.utils.ImageDownloadProgressListener
import com.example.scrollinggallery.ui.adapter.utils.ResourceDownloadingListener

class PictureHolder(
            parent: ViewGroup
): RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_picture, parent, false)
){

    @SuppressLint("ClickableViewAccessibility")
    fun bind(picture: Pic) {
        itemView.listPictureTextName.text = picture.author
        itemView.listPictureToggleLike.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                setImageIsLiked(picture)
            }
        }
        val detector = GestureDetector(itemView.context, DoubleTapListener(itemView.listPictureToggleLike))
        itemView.listPictureImage.setOnTouchListener { _, event -> detector.onTouchEvent(event) }

        loadImage(picture.url)
    }

    fun setImageIsLiked(pic: Pic){
        Toast.makeText(itemView.context, pic.id.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun loadImage(url: String){
        val progressListener = ImageDownloadProgressListener(
            itemView.listPictureProgressBar,
            itemView.listPictureTextName,
            itemView.listPictureToggleLike
        )
        val downloadListener = ResourceDownloadingListener(
            itemView.listPictureTextName,
            itemView.listPictureToggleLike
        )

        ProgressManager.getInstance()
            .addResponseListener(url, progressListener)
        Glide.with(itemView.context)
            .load(url)
            .listener(downloadListener)
            .into(itemView.listPictureImage)
    }
}