package com.example.scrollinggallery.ui.main.local

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.example.scrollinggallery.data.LocalPicsRepository
import com.example.scrollinggallery.domain.Status
import com.example.scrollinggallery.ui.base.BaseViewModel

class LocalViewModel @ViewModelInject constructor(
            localRepository: LocalPicsRepository
): BaseViewModel(localRepository) {

    override var pagedList = getLiveDataPagedList(localRepository)

    var listStatus = MutableLiveData<Status>()
}