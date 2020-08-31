package com.example.scrollinggallery.ui.adapter

import android.annotation.SuppressLint
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scrollinggallery.R
import com.example.scrollinggallery.data.LocalRepository
import com.example.scrollinggallery.data.PicturesMapper
import com.example.scrollinggallery.domain.Pic
import kotlinx.android.synthetic.main.list_item_picture.view.*
import me.jessyan.progressmanager.ProgressManager
import com.example.scrollinggallery.ui.adapter.utils.DoubleTapListener
import com.example.scrollinggallery.ui.adapter.utils.ImageDownloadProgressListener
import com.example.scrollinggallery.ui.adapter.utils.ResourceDownloadingListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class PictureHolder(
            parent: ViewGroup
): RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_picture, parent, false)
){

    @SuppressLint("ClickableViewAccessibility")
    fun bind(picture: Pic) {
        itemView.listPictureTextName.text = picture.author


        itemView.listPictureToggleLike.isChecked = picture.isLiked


        itemView.listPictureToggleLike.setOnClickListener {
            if(picture.isLiked){
                picture.isLiked = false
                removeFromDB(picture)
                itemView.listPictureToggleLike.isChecked = false
            }
            else{
                picture.isLiked = true
                saveToDB(picture)
                itemView.listPictureToggleLike.isChecked = true
            }

        }

        //itemView.listPictureToggleLike.setOnCheckedChangeListener { _, isChecked ->
        //    if (isChecked){
        //        picture.isLiked = true
        //        saveToDB(picture)
        //        Timber.e("Go to database")
        //    }
        //    else{
        //        picture.isLiked = false
        //    }
//
        //    //else
        //        //removeFromDB(picture)
        //}

        val detector = GestureDetector(itemView.context, DoubleTapListener(itemView.listPictureToggleLike))
        itemView.listPictureImage.setOnTouchListener { _, event -> detector.onTouchEvent(event) }

        loadImage(picture.url)
    }

    private fun saveToDB(pic: Pic){
        //if(pic.isLiked){
            GlobalScope.launch {
                LocalRepository().addToDB(pic)
            }
        //}
    }

    private fun removeFromDB(pic: Pic){
        GlobalScope.launch {
            LocalRepository().removeFromDB(pic)
        }
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