package com.example.scrollinggallery.ui.adapters.recycler

import com.example.scrollinggallery.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.scrollinggallery.network.data.PicsumDTO
import timber.log.Timber

class PicsumAdapter: PagedListAdapter<PicsumDTO, PictureHolder>(PicturesDiffCallback) {

    companion object {
        val PicturesDiffCallback = object : DiffUtil.ItemCallback<PicsumDTO>() {
            override fun areItemsTheSame(oldItem: PicsumDTO, newItem: PicsumDTO) =
                oldItem.download_url == newItem.download_url

            override fun areContentsTheSame(oldItem: PicsumDTO, newItem: PicsumDTO) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_picture, parent, false)
        return PictureHolder(view)
    }

    override fun onBindViewHolder(holder: PictureHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            getItem(position)?.let { holder.bind(it) }
        } else {
            Timber.e("Null item")
        }
    }
}