package com.example.scrollinggallery.ui.adapters.recycler.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.scrollinggallery.network.data.PicsumDTO

class PicsumDataSourceFactory: DataSource.Factory<Int, PicsumDTO>() {

    val picsumLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, PicsumDTO>>()

    override fun create(): DataSource<Int, PicsumDTO> {
        val picDataSource = PicsumDataSource()

        picsumLiveDataSource.postValue(picDataSource)

        return picDataSource
    }
}