package com.example.scrollinggallery.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.scrollinggallery.domain.Pic

//(val adapterOnClick : (Any) -> Unit)
class PicsumAdapter: PagedListAdapter<Pic, PictureHolder>(PicturesDiffCallback) {

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
        private val PicturesDiffCallback = object : DiffUtil.ItemCallback<Pic>() {
            override fun areItemsTheSame(oldItem: Pic, newItem: Pic) =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: Pic, newItem: Pic) =
                oldItem == newItem
        }
    }
}