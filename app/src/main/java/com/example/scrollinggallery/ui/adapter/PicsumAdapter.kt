package com.example.scrollinggallery.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.domain.PicDetailed

//(val adapterOnClick : (Any) -> Unit)
class PicsumAdapter: PagedListAdapter<PicDetailed, PictureHolder>(PicturesDiffCallback) {

    override fun onBindViewHolder(holder: PictureHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PictureHolder(parent)

    /*  Переопределение этих методов нужно для решения проблемы, возникающей, когда пользователь
    видит на холдерах, вытащенных из пула ресайклера, старые значения*/
    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    companion object {
        private val PicturesDiffCallback = object : DiffUtil.ItemCallback<PicDetailed>() {
            override fun areItemsTheSame(oldItem: PicDetailed, newItem: PicDetailed) =
                oldItem.pic == newItem.pic

            override fun areContentsTheSame(oldItem: PicDetailed, newItem: PicDetailed) =
                oldItem.pic.url == newItem.pic.url
        }
    }
}