package com.example.scrollinggallery.ui.adapter

import android.annotation.SuppressLint
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scrollinggallery.R
import com.example.scrollinggallery.data.LocalRepository
import com.example.scrollinggallery.domain.Pic
import kotlinx.android.synthetic.main.list_item_picture.view.*
import me.jessyan.progressmanager.ProgressManager
import com.example.scrollinggallery.ui.adapter.extensions.DoubleTapListener
import com.example.scrollinggallery.ui.adapter.extensions.ImageDownloadProgressListener
import com.example.scrollinggallery.ui.adapter.extensions.ResourceDownloadingListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PictureHolder(
            parent: ViewGroup
): RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_picture, parent, false)
){

    lateinit var pic: Pic

    fun bind(picture: Pic) {
        this.pic = picture

        initHolder()
        loadImage()
    }

    private fun saveToDB(){   //todo: вынести из ui
        GlobalScope.launch {
            LocalRepository().addToDB(pic)
        }
    }

    private fun removeFromDB(){
        GlobalScope.launch {
            LocalRepository().removeFromDB(pic)
        }
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
                removeFromDB()
                itemView.listPictureToggleLike.isChecked = false
            }
            else{
                pic.isLiked = true
                saveToDB()
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