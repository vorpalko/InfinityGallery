package com.example.scrollinggallery.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.scrollinggallery.data.PicsRepository
import com.example.scrollinggallery.data.model.ResponseDTO
import kotlinx.coroutines.CoroutineScope

class PicsDataSourceFactory(
                private val scope: CoroutineScope,
                private val repo: PicsRepository
): DataSource.Factory<Int, Pic>() {

    override fun create(): DataSource<Int, Pic> {
        val picDataSource = PicsDataSource(scope, repo)
        val picsumLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Pic>>()

        picsumLiveDataSource.postValue(picDataSource)

        return picDataSource
    }
}