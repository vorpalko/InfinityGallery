package com.example.scrollinggallery.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scrollinggallery.R
import com.example.scrollinggallery.data.LocalRepository
import com.example.scrollinggallery.data.PicsRepository
import com.example.scrollinggallery.data.RemoteRepository
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.ui.adapter.PicsumAdapter
import com.example.scrollinggallery.domain.DataSourceViewModel
import com.example.scrollinggallery.domain.DataSourceViewModelFactory
import com.example.scrollinggallery.ui.adapter.extensions.PictureCardDecoration
import kotlinx.android.synthetic.main.fragment_recycler.*

class RecyclerFragment : Fragment(){

    var isLocalStorage = true

    private val picsumAdapter = PicsumAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_recycler, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycler()

        setupList(repoToSetData())

        button.setOnClickListener {
            setupList(repoToSetData())
        }
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

    private fun repoToSetData(): PicsRepository{
        isLocalStorage = !isLocalStorage
        return if(isLocalStorage)
            LocalRepository()
        else
            RemoteRepository()
    }

    private fun setupList(storage: PicsRepository){
        viewModelStore.clear()
        val picsumViewModel: DataSourceViewModel
                by viewModels { DataSourceViewModelFactory(storage) }

        picsumViewModel.itemPagedList.observe(viewLifecycleOwner, { pics ->
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