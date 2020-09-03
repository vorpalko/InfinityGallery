package com.example.scrollinggallery.ui.main

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.scrollinggallery.data.PicsRepository
import com.example.scrollinggallery.data.RepositoryProvider
import com.example.scrollinggallery.data.util.PAGE_SIZE
import com.example.scrollinggallery.domain.DataType
import com.example.scrollinggallery.domain.PicsDataSourceFactory
import com.example.scrollinggallery.domain.Status

class PicsViewModel(private var fragment: PicsFragment): ViewModel() {

    var pagedList = getLiveDataPagedList(RepositoryProvider().getRemoteRepo())
    var sourceState = MutableLiveData<DataType>()
    var listStatus = MutableLiveData<Status>()

    private var initialSourceState = DataType.REMOTE

    init {
        sourceState = MutableLiveData(initialSourceState)
    }

    fun retryConnection(){
        sourceState.postValue(DataType.REMOTE)
        pagedList = getLiveDataPagedList(RepositoryProvider().getRemoteRepo())
    }

    fun swapSourceState(fragment: PicsFragment){
        this.fragment = fragment
        if(sourceState.value == DataType.LOCAL)
            sourceState.postValue(DataType.REMOTE)
        else
            sourceState.postValue(DataType.LOCAL)
    }

    fun changeRepo(state: DataType){
        if(state != initialSourceState){
            initialSourceState = initialSourceState.swap(initialSourceState)

            val repository = if (state == DataType.LOCAL)
                RepositoryProvider().getLocalRepo()
            else
                RepositoryProvider().getRemoteRepo()

            pagedList = getLiveDataPagedList(repository)
        }
    }

    private fun getLiveDataPagedList(repository: PicsRepository) =
        LivePagedListBuilder(PicsDataSourceFactory(repository, fragment), PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(PAGE_SIZE)
                .build()).build()
}