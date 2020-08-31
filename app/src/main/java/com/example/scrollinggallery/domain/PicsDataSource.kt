package com.example.scrollinggallery.domain

import androidx.paging.PageKeyedDataSource
import com.example.scrollinggallery.data.LocalRepository
import com.example.scrollinggallery.data.PicsRepository
import com.example.scrollinggallery.data.RemoteRepository
import com.example.scrollinggallery.data.db.PicsDatabase
import com.example.scrollinggallery.network.utils.FIRST_PAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PicsDataSource(
            private val scope: CoroutineScope,
            //private val repository: PicsRepository
            private val repository: PicsRepository
): PageKeyedDataSource<Int, Pic>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Pic>
    ) {
        scope.launch {
            val data = repository.getList(FIRST_PAGE)
            callback.onResult(data, null, FIRST_PAGE + 1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Pic>
    ) {
        scope.launch {
            val data = repository.getList(params.key)
            callback.onResult(data, decremented(params.key))
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Pic>
    ) {
        scope.launch {
            val data = repository.getList(params.key)
            callback.onResult(data,params.key + 1)
        }
    }
}