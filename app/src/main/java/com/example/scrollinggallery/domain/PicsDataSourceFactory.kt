package com.example.scrollinggallery.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.scrollinggallery.data.PicsRepository
import com.example.scrollinggallery.ui.main.PicsFragment

class PicsDataSourceFactory(
            private val repo: PicsRepository,
            private val fragment: PicsFragment
): DataSource.Factory<Int, PicDetailed>() {

    override fun create(): DataSource<Int, PicDetailed> {
        val picDataSource = PicsDataSource(repo, fragment)
        val picsumLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, PicDetailed>>()

        picsumLiveDataSource.postValue(picDataSource)

        return picDataSource
    }
}