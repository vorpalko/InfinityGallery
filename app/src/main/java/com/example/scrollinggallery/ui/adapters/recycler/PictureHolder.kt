package com.example.scrollinggallery.ui.adapters.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollinggallery.data.Picture
import kotlinx.android.synthetic.main.list_item_picture.view.*

class PictureHolder(
                itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(picture: Picture) {
        itemView.listPictureTextName.text = picture.author
        //itemView.listPictureImage
    }
}