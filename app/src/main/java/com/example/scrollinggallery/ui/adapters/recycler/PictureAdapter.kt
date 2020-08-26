package com.example.scrollinggallery.ui.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollinggallery.data.Picture
import com.example.scrollinggallery.R

class PictureAdapter: RecyclerView.Adapter<PictureHolder>() {

    private val pictureList = ArrayList<Picture>()

    fun addData(pictures: List<Picture>){
        pictureList.addAll(pictures)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PictureHolder, position: Int) { holder.bind(pictureList[position]) }

    override fun getItemCount() = pictureList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_picture, parent, false)
        return PictureHolder(view)
    }
}