package com.example.scrollinggallery.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scrollinggallery.R
import com.example.scrollinggallery.domain.Status
import com.example.scrollinggallery.ui.adapter.PicsumAdapter
import com.example.scrollinggallery.ui.adapter.extensions.PictureCardDecorator
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.android.synthetic.main.view_error_recycler.*

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

        initRecycler()
        setupList()

        layoutErrorButtonRetry.setOnClickListener {
            picsumViewModel.retryConnection()
        }
    }

    fun newInstance() =
        PicsFragment()

    fun changeRepositoryType(){
        picsumViewModel.swapSourceState(this)
    }


    private fun setupList(){
        picsumViewModel = ViewModelProvider(this, PicsViewModelFactory(this)).get(PicsViewModel::class.java)

        picsumViewModel.listStatus.observe(viewLifecycleOwner, { status ->
            if (status != null)
                showLayer(status)
            else
                showLayer(Status.ERROR)
        })

        picsumViewModel.sourceState.observe(viewLifecycleOwner, { state ->
            picsumViewModel.changeRepo(state)

            picsumViewModel.pagedList.observe(viewLifecycleOwner, { pics ->
                //pics.dataSource.invalidate()
                picsumAdapter.submitList(pics)
            })
        })
    }

    private fun showLayer(status: Status) {
        if (status == Status.ERROR){
            fragmentRecyclerList.visibility = View.GONE
            layoutError.visibility = View.VISIBLE
        } else {
            fragmentRecyclerList.visibility = View.VISIBLE
            layoutError.visibility = View.GONE
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