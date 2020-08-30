package com.example.scrollinggallery.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scrollinggallery.R
import com.example.scrollinggallery.data.model.ResponseDTO
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.ui.adapter.PicsumAdapter
import com.example.scrollinggallery.ui.adapter.PicsumViewModel
import com.example.scrollinggallery.ui.adapter.utils.PictureCardDecoration
import kotlinx.android.synthetic.main.fragment_recycler.*

class RecyclerFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_recycler, container, false)

    private val picsumAdapter = PicsumAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycler()
        initObserve()
    }

    fun newInstance() =
        RecyclerFragment()

    private fun initRecycler(){
        fragmentRecyclerList.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(PictureCardDecoration())
            picsumAdapter.setHasStableIds(true)
            adapter = picsumAdapter
        }
    }

    private fun initObserve(){
        val picsumViewModel = ViewModelProvider(this).get(PicsumViewModel::class.java)
        picsumViewModel.itemPagedList.observe(this, { pics ->
            picsumAdapter.submitList(pics)
            //pics?.let { showLayer(pics) }
        })
    }

    private fun showLayer(pics: PagedList<Pic>) {
        picsumAdapter.submitList(pics)
        if (pics.isEmpty()){
            fragmentRecyclerList.visibility = View.GONE
            fragmentRecyclerErrorLayout.visibility = View.VISIBLE
        } else {
            fragmentRecyclerList.visibility = View.VISIBLE
            fragmentRecyclerErrorLayout.visibility = View.GONE
        }
    }
}