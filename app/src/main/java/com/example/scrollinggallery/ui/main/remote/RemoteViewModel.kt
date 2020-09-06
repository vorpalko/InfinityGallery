package com.example.scrollinggallery.ui.main.remote

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.scrollinggallery.data.LocalPicsRepository
import com.example.scrollinggallery.data.PicsRepository
import com.example.scrollinggallery.data.RemotePicsRepository
import com.example.scrollinggallery.data.util.PAGE_SIZE
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.domain.PicDetailed
import com.example.scrollinggallery.domain.PicsDataSourceFactory
import com.example.scrollinggallery.domain.Status
import kotlinx.coroutines.launch

class RemoteViewModel @ViewModelInject constructor(
            private val networkRepository: RemotePicsRepository,
            private val localRep: LocalPicsRepository
): ViewModel() {

    var pagedList = getLiveDataPagedList(networkRepository)

    var listStatus = MutableLiveData<Status>()

    fun retryConnection(){
        pagedList.value?.dataSource?.invalidate()
        pagedList = getLiveDataPagedList(networkRepository)
    }

    fun addToDB(pic: Pic){
        viewModelScope.launch {
            localRep.addToDB(pic)
        }
    }

    fun removeFromDB(pic: Pic){
        viewModelScope.launch {
            localRep.removeFromDB(pic)
        }
    }

    private fun getLiveDataPagedList(repository: PicsRepository) =
         LivePagedListBuilder(PicsDataSourceFactory(repository, this), PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(PAGE_SIZE)
            .build()).build()
}