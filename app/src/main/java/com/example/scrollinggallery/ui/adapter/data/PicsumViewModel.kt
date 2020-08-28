package com.example.scrollinggallery.ui.adapter.data

import com.example.scrollinggallery.network.util.PAGE_SIZE
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.scrollinggallery.network.data.PicsumDTO

class PicsumViewModel : ViewModel() {

    var itemPagedList: LiveData<PagedList<PicsumDTO>>
    var liveDataSource: LiveData<PageKeyedDataSource<Int, PicsumDTO>>

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(PAGE_SIZE).build()

        val itemDataSourceFactory = PicsumDataSourceFactory()

        liveDataSource = PicsumDataSourceFactory().picsumLiveDataSource

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)
            .build()
    }
}