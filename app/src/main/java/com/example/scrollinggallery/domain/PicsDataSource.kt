package com.example.scrollinggallery.domain

import androidx.lifecycle.ViewModelProvider
import androidx.paging.PageKeyedDataSource
import com.example.scrollinggallery.data.LocalRepository
import com.example.scrollinggallery.data.PicsRepository
import com.example.scrollinggallery.data.RemoteRepository
import com.example.scrollinggallery.data.util.FIRST_PAGE
import com.example.scrollinggallery.data.util.decremented
import com.example.scrollinggallery.ui.main.PicsViewModelFactory
import com.example.scrollinggallery.ui.main.PicsFragment
import com.example.scrollinggallery.ui.main.PicsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PicsDataSource(
            private val repository: PicsRepository,
            fragment: PicsFragment
): PageKeyedDataSource<Int, PicDetailed>() {

    private val picsumViewModel = ViewModelProvider(fragment, PicsViewModelFactory(fragment)).get(PicsViewModel::class.java)

    private fun getNetworkStatus(data: List<PicDetailed>) {
        var state = Status.SUCCESS
        if(repository is RemoteRepository){
            state = if(data.isEmpty()){
                Status.ERROR
            } else{
                data[0].state
            }
        }

        picsumViewModel.listStatus.postValue(state)
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PicDetailed>
    ) {
        GlobalScope.launch {//todo: change scope
            val data = repository.getList(FIRST_PAGE)
            getNetworkStatus(data)
            callback.onResult(data, null, FIRST_PAGE + 1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PicDetailed>
    ) {
        GlobalScope.launch {
            val data = repository.getList(params.key)
            getNetworkStatus(data)
            callback.onResult(data, decremented(params.key))
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PicDetailed>
    ) {
        GlobalScope.launch {
            val data = repository.getList(params.key)
            getNetworkStatus(data)
            callback.onResult(data,params.key + 1)
        }
    }
}