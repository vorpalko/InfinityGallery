package com.example.scrollinggallery.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.example.scrollinggallery.domain.PicDetailed
import com.example.scrollinggallery.ui.adapter.list_utils.PictureDiffUtilCallback
import com.example.scrollinggallery.ui.main.PicItemCallback

class PicsumAdapter(
            private val picItemCallback: PicItemCallback
): PagedListAdapter<PicDetailed, PictureHolder>(PictureDiffUtilCallback()) {

    override fun onBindViewHolder(holder: PictureHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(item, picItemCallback) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PictureHolder(parent)

    /*  Переопределение этих методов нужно для решения проблемы, возникающей, когда пользователь
    видит на холдерах, вытащенных из пула ресайклера, старые значения*/
    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position
}