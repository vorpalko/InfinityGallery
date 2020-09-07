package com.example.scrollinggallery.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import com.example.scrollinggallery.data.LocalPicsRepository
import com.example.scrollinggallery.data.PicsRepository
import com.example.scrollinggallery.data.RemotePicsRepository
import com.example.scrollinggallery.data.util.FIRST_PAGE
import com.example.scrollinggallery.ui.main.local.LocalViewModel
import com.example.scrollinggallery.ui.main.remote.RemoteViewModel
import kotlinx.coroutines.launch

fun decremented(value: Int) =
    if(value > 1) value.dec() else value

class PicsDataSource(
            private val repository: PicsRepository,
            private val vm: ViewModel
): PageKeyedDataSource<Int, PicDetailed>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PicDetailed>
    ) {
        vm.viewModelScope.launch {
            val data = repository.getList(FIRST_PAGE)
            getNetworkStatus(data)
            getFullnessStatus(data)
            callback.onResult(data, null, FIRST_PAGE + 1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PicDetailed>
    ) {
        vm.viewModelScope.launch {
            val data = repository.getList(params.key)
            getNetworkStatus(data)
            callback.onResult(data, decremented(params.key))
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PicDetailed>
    ) {
        vm.viewModelScope.launch {
            val data = repository.getList(params.key)
            getNetworkStatus(data)
            callback.onResult(data,params.key + 1)
        }
    }

    private fun getNetworkStatus(data: List<PicDetailed>) {
        if(repository is RemotePicsRepository){
            var state = Status.SUCCESS
            state = if(data.isEmpty()){
                Status.ERROR
            } else{
                data[0].state
            }
            if(vm is RemoteViewModel)
                vm.listStatus.postValue(state)
        }
    }

    private fun getFullnessStatus(data: List<PicDetailed>){
        if(repository is LocalPicsRepository){
            var state = Status.SUCCESS
            if(data.isEmpty())
                state = Status.ERROR
            if(vm is LocalViewModel)
                vm.listStatus.postValue(state)
        }
    }

}