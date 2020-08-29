package com.example.scrollinggallery.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scrollinggallery.R
import com.example.scrollinggallery.ui.adapter.PicsumAdapter
import com.example.scrollinggallery.ui.adapter.data.PicsumViewModel
import com.example.scrollinggallery.ui.adapter.utils.PictureCardDecoration
import kotlinx.android.synthetic.main.fragment_recycler.*

class RecyclerFragment : Fragment(){

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

    private fun initRecycler(){
        val picsumViewModel = ViewModelProvider(this).get(PicsumViewModel::class.java)
        val picsumAdapter = PicsumAdapter()
        picsumAdapter.setHasStableIds(true)

        picsumViewModel.itemPagedList.observe(this, {
                pics -> picsumAdapter.submitList(pics)
        })

        fragmentRecyclerList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = picsumAdapter
            addItemDecoration(PictureCardDecoration())
        }
    }
}