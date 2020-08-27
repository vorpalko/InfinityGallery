package com.example.scrollinggallery.ui.adapters.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.scrollinggallery.R
import com.example.scrollinggallery.network.data.PicsumDTO
import kotlinx.android.synthetic.main.list_item_picture.view.*

class PictureHolder(
                itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(picture: PicsumDTO) {
        itemView.listPictureTextName.text = picture.author
        Glide.with(itemView.context)
            .load(picture.download_url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(itemView.listPictureImage)
    }
}