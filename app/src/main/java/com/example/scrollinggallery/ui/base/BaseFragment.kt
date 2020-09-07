package com.example.scrollinggallery.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.scrollinggallery.R
import com.example.scrollinggallery.ui.adapter.PicsumAdapter
import com.example.scrollinggallery.ui.adapter.list_utils.PictureCardDecorator
import com.example.scrollinggallery.ui.main.PicItemCallback
import kotlinx.android.synthetic.main.fragment_recycler.*

abstract class BaseFragment: Fragment(), PicItemCallback {

    val picsumAdapter = PicsumAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_recycler, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        setupList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentRecyclerList.adapter = null
    }

    open fun setupList(){}

    private fun initRecycler(){
        fragmentRecyclerList.apply {
            addItemDecoration(PictureCardDecorator())
            picsumAdapter.setHasStableIds(true)
            adapter = picsumAdapter
        }
    }
}