package com.example.scrollinggallery.ui.main.remote

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.example.scrollinggallery.data.LocalPicsRepository
import com.example.scrollinggallery.data.RemotePicsRepository
import com.example.scrollinggallery.domain.Status
import com.example.scrollinggallery.ui.base.BaseViewModel

class RemoteViewModel @ViewModelInject constructor(
            private val networkRepository: RemotePicsRepository,
            localRepository: LocalPicsRepository
): BaseViewModel(localRepository) {

    override var pagedList = getLiveDataPagedList(networkRepository)

    var listStatus = MutableLiveData<Status>()

    fun retryConnection(){
        pagedList.value?.dataSource?.invalidate()
        pagedList = getLiveDataPagedList(networkRepository)
    }
}