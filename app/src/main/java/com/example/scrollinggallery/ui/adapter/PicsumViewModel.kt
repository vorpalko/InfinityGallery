package com.example.scrollinggallery.ui.adapter

import com.example.scrollinggallery.network.utils.PAGE_SIZE
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.scrollinggallery.domain.PicsDataSourceFactory

class PicsumViewModel: ViewModel() {
    private val dataSourceFactory = PicsDataSourceFactory(viewModelScope)

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setPageSize(PAGE_SIZE)
        .build()

    var itemPagedList = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        .build()
}