package com.example.scrollinggallery.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scrollinggallery.network.data.PicsumDTO
import kotlinx.android.synthetic.main.list_item_picture.view.*
import me.jessyan.progressmanager.ProgressManager

class PictureHolder(
         itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(picture: PicsumDTO) {
        itemView.listPictureTextName.text = picture.author
        loadImage(picture.download_url)
    }

    private fun loadImage(url: String){
        val listener = GlideListenerProvider()
            .getGlideListener(itemView.listPictureProgressBar, itemView.listPictureTextName)
        ProgressManager.getInstance()
            .addResponseListener(url, listener)

        Glide.with(itemView.context)
            .load(url)
            .into(itemView.listPictureImage)
    }
}