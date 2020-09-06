package com.example.scrollinggallery.ui.main.local

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.scrollinggallery.data.LocalPicsRepository
import com.example.scrollinggallery.data.PicsRepository
import com.example.scrollinggallery.data.util.PAGE_SIZE
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.domain.PicsDataSourceFactory
import kotlinx.coroutines.launch

class LocalViewModel @ViewModelInject constructor(
            private val localRepository: LocalPicsRepository
): ViewModel() {

    var pagedList = getLiveDataPagedList(localRepository)

    fun addToDB(pic: Pic){
        viewModelScope.launch {
            localRepository.addToDB(pic)
        }
    }

    fun removeFromDB(pic: Pic){
        viewModelScope.launch {
            localRepository.removeFromDB(pic)
            pagedList.value?.dataSource?.invalidate()
        }
    }

    private fun getLiveDataPagedList(repository: PicsRepository) =
        LivePagedListBuilder(PicsDataSourceFactory(repository, this), PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(PAGE_SIZE)
            .build()).build()
}