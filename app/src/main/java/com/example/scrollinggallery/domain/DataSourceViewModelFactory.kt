package com.example.scrollinggallery.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scrollinggallery.data.PicsRepository

class DataSourceViewModelFactory(private val repo: PicsRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = DataSourceViewModel(repo) as T
}