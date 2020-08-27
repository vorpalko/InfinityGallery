package com.example.scrollinggallery.ui.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollinggallery.R
import com.example.scrollinggallery.network.data.PicsumDTO

class PictureAdapter: RecyclerView.Adapter<PictureHolder>() {

    private val pictures = ArrayList<PicsumDTO>()

    fun addData(data: List<PicsumDTO>){
        pictures.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PictureHolder, position: Int) { holder.bind(pictures[position]) }

    override fun getItemCount() = pictures.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_picture, parent, false)
        return PictureHolder(view)
    }
}