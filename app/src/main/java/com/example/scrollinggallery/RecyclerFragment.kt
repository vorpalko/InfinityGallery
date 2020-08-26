package com.example.scrollinggallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scrollinggallery.data.Picture
import com.example.scrollinggallery.ui.adapters.recycler.PictureCardDecoration
import com.example.scrollinggallery.ui.adapters.recycler.PictureAdapter
import kotlinx.android.synthetic.main.fragment_recycler.*
import java.util.*

class RecyclerFragment : Fragment(){

    private val pictureAdapter = PictureAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_recycler, container, false)

    fun newInstance() = RecyclerFragment()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycler()
    }

    private fun setTestData(count: Int){
        val mocks = ArrayList<Picture>(count)

        for(i in 1..count){
            mocks.add(Picture("Test"))
        }
        pictureAdapter.addData(mocks)
    }

    private fun initRecycler(){
        fragmentRecyclerList.layoutManager = LinearLayoutManager(activity)
        setTestData(60)
        fragmentRecyclerList.adapter = pictureAdapter
        fragmentRecyclerList.addItemDecoration(PictureCardDecoration())
    }
}