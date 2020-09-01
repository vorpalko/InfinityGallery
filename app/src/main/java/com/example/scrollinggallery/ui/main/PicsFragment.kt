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
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.ui.adapter.PicsumAdapter
import com.example.scrollinggallery.ui.adapter.extensions.PictureCardDecorator
import kotlinx.android.synthetic.main.fragment_recycler.*

class PicsFragment : Fragment(){

    private lateinit var picsumViewModel: PicsViewModel

    private val picsumAdapter = PicsumAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_recycler, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        picsumViewModel = ViewModelProvider(this).get(PicsViewModel::class.java)

        initRecycler()
        setupList()
    }

    fun changeRepositoryType() =
        picsumViewModel.swapSourceState()

    fun newInstance() =
        PicsFragment()

    private fun setupList(){
        picsumViewModel.sourceState.observe(viewLifecycleOwner, { state ->
            picsumViewModel.changeRepo(state)

            picsumViewModel.pagedList.observe(viewLifecycleOwner, { pics ->
                //pics.dataSource.invalidate()
                picsumAdapter.submitList(pics)
            })
        })
        //picsumViewModel.listStatus.observe(viewLifecycleOwner, { status ->
        //
        //})
    }

    private fun showLayer(pics: PagedList<Pic>) {
        if (pics.isEmpty()){
            fragmentRecyclerList.visibility = View.GONE
            fragmentRecyclerErrorLayout.visibility = View.VISIBLE
        } else {
            fragmentRecyclerList.visibility = View.VISIBLE
            fragmentRecyclerErrorLayout.visibility = View.GONE
        }
    }

    private fun initRecycler(){
        fragmentRecyclerList.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(PictureCardDecorator())
            picsumAdapter.setHasStableIds(true)
            adapter = picsumAdapter
        }
    }
}