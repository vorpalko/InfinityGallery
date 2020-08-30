package com.example.scrollinggallery.domain

import androidx.paging.PageKeyedDataSource
import com.example.scrollinggallery.data.RemoteRepository
import com.example.scrollinggallery.network.utils.FIRST_PAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class PicsDataSource(
            private val scope: CoroutineScope
): PageKeyedDataSource<Int, Pic>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Pic>
    ) {
        scope.launch {
            val data = RemoteRepository().getList(1)
            callback.onResult(data, null, FIRST_PAGE + 1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Pic>
    ) {
        scope.launch {
            val data = RemoteRepository().getList(params.key)

            var key = params.key
            if(key > 1)
                key--
            callback.onResult(data, key)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Pic>
    ) {
        scope.launch {
            val data = RemoteRepository().getList(params.key)
            callback.onResult(data,params.key + 1)
        }
    }
}