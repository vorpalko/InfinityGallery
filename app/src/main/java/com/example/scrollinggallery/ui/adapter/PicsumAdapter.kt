package com.example.scrollinggallery.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.scrollinggallery.R
import com.example.scrollinggallery.network.data.PicsumDTO

class PicsumAdapter: PagedListAdapter<PicsumDTO, PictureHolder>(PicturesDiffCallback) {

    companion object {
        val PicturesDiffCallback = object : DiffUtil.ItemCallback<PicsumDTO>() {
            override fun areItemsTheSame(oldItem: PicsumDTO, newItem: PicsumDTO) =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: PicsumDTO, newItem: PicsumDTO) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_picture, parent, false)
        return PictureHolder(view)
    }

    override fun onBindViewHolder(holder: PictureHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

/*  Переопределение этих методов нужно для решения проблемы, возникающей, когда пользователь
    видит на холдерах, вытащенных из пула ресайклера, старые значения*/

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) =  position
}