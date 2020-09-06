package com.example.scrollinggallery.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.scrollinggallery.data.PicsRepository
import timber.log.Timber

class PicsDataSourceFactory(
            private val repo: PicsRepository,
            private val vm: ViewModel
): DataSource.Factory<Int, PicDetailed>() {

    override fun create(): DataSource<Int, PicDetailed> {
        val picDataSource = PicsDataSource(repo, vm)
        val picsumLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, PicDetailed>>()

        picsumLiveDataSource.postValue(picDataSource)

        return picDataSource
    }
}