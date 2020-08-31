package com.example.scrollinggallery.domain

import com.example.scrollinggallery.network.utils.PAGE_SIZE
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.scrollinggallery.data.PicsRepository
import com.example.scrollinggallery.domain.PicsDataSourceFactory

class DataSourceViewModel(
         repo: PicsRepository
): ViewModel() {

    private val dataSourceFactory = PicsDataSourceFactory(viewModelScope, repo)

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setPageSize(PAGE_SIZE)
        .build()

    var itemPagedList = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        .build()
}