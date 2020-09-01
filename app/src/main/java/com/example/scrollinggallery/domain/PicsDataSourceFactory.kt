package com.example.scrollinggallery.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.scrollinggallery.data.PicsRepository
import com.example.scrollinggallery.data.util.PAGE_SIZE
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.domain.PicsDataSource
import kotlinx.coroutines.CoroutineScope

class PicsDataSourceFactory(
            private val repo: PicsRepository
): DataSource.Factory<Int, Pic>() {

    override fun create(): DataSource<Int, Pic> {
        val picDataSource = PicsDataSource(repo)
        val picsumLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Pic>>()

        picsumLiveDataSource.postValue(picDataSource)

        return picDataSource
    }
}