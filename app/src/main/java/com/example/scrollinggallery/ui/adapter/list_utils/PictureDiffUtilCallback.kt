package com.example.scrollinggallery.ui.adapter.list_utils

import androidx.recyclerview.widget.DiffUtil
import com.example.scrollinggallery.domain.PicDetailed

class PictureDiffUtilCallback: DiffUtil.ItemCallback<PicDetailed>() {

    override fun areItemsTheSame(oldItem: PicDetailed, newItem: PicDetailed) =
        oldItem.pic == newItem.pic

    override fun areContentsTheSame(oldItem: PicDetailed, newItem: PicDetailed) =
        oldItem.pic.url == newItem.pic.url
}