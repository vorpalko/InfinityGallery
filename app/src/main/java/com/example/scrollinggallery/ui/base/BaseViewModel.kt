package com.example.scrollinggallery.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.scrollinggallery.data.LocalPicsRepository
import com.example.scrollinggallery.data.PicsRepository
import com.example.scrollinggallery.data.util.PAGE_SIZE
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.domain.PicDetailed
import com.example.scrollinggallery.domain.PicsDataSourceFactory
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val localRepository: LocalPicsRepository): ViewModel() {

    abstract var pagedList: LiveData<PagedList<PicDetailed>>

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

    fun getLiveDataPagedList(repository: PicsRepository) =
        LivePagedListBuilder(
            PicsDataSourceFactory(repository, this), PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(PAGE_SIZE)
            .build()).build()
}