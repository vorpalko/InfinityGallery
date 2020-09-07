package com.example.scrollinggallery.ui.adapter

import android.annotation.SuppressLint
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scrollinggallery.R
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.domain.PicDetailed
import kotlinx.android.synthetic.main.list_item_picture.view.*
import me.jessyan.progressmanager.ProgressManager
import com.example.scrollinggallery.ui.adapter.list_utils.DoubleTapListener
import com.example.scrollinggallery.ui.adapter.list_utils.ImageDownloadProgressListener
import com.example.scrollinggallery.ui.adapter.list_utils.ResourceDownloadingListener
import com.example.scrollinggallery.ui.main.PicItemCallback

class PictureHolder(
            parent: ViewGroup
): RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_picture, parent, false)
){

    lateinit var pic: Pic
    private lateinit var picItemCallback: PicItemCallback

    fun bind(picture: PicDetailed, picItemCallback: PicItemCallback) {
        this.picItemCallback = picItemCallback
        this.pic = picture.pic

        initHolder()
        loadImage()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initHolder(){
        val detector = GestureDetector(itemView.context, DoubleTapListener(itemView.listPictureToggleLike))
        itemView.listPictureImage.setOnTouchListener { _, event -> detector.onTouchEvent(event) }
        itemView.listPictureTextName.text = pic.author
        itemView.listPictureToggleLike.isChecked = pic.isLiked
        itemView.listPictureToggleLike.setOnClickListener {
            if(pic.isLiked){
                pic.isLiked = false
                picItemCallback.deletePicCallback(pic)
                itemView.listPictureToggleLike.isChecked = false
            }
            else{
                pic.isLiked = true
                picItemCallback.savePicCallback(pic)
                itemView.listPictureImageLike.start()
                itemView.listPictureToggleLike.isChecked = true
            }
        }
    }

    private fun loadImage(){
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
            .addResponseListener(pic.url, progressListener)
        Glide.with(itemView.context)
            .load(pic.url)
            .listener(downloadListener)
            .into(itemView.listPictureImage)
    }
}