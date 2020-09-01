package com.example.scrollinggallery.domain

import androidx.paging.PageKeyedDataSource
import com.example.scrollinggallery.data.PicsRepository
import com.example.scrollinggallery.data.util.FIRST_PAGE
import com.example.scrollinggallery.data.util.decremented
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PicsDataSource(
            private val repository: PicsRepository
): PageKeyedDataSource<Int, Pic>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Pic>
    ) {
        GlobalScope.launch {//todo: change scope
            val data = repository.getList(FIRST_PAGE)
            callback.onResult(data, null, FIRST_PAGE + 1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Pic>
    ) {
        GlobalScope.launch {
            val data = repository.getList(params.key)
            callback.onResult(data, decremented(params.key))
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Pic>
    ) {
        GlobalScope.launch {
            val data = repository.getList(params.key)
            callback.onResult(data,params.key + 1)
        }
    }
}