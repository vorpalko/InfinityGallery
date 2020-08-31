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
import com.example.scrollinggallery.ui.adapter.extensions.PictureCardDecorator
import kotlinx.android.synthetic.main.fragment_recycler.*

class PicsListFragment : Fragment(){

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
    }

    fun changeRepositoryType(){
        setupList(repoToSetData())
    }

    fun newInstance() =
        PicsListFragment()

    private fun initRecycler(){
        fragmentRecyclerList.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(PictureCardDecorator())
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
            //showLayer(pics)
            //pics.dataSource.invalidate()
            picsumAdapter.submitList(pics)
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