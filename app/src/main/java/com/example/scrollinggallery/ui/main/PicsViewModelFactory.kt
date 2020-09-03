package com.example.scrollinggallery.ui.main

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PicsViewModelFactory(private val act: PicsFragment) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(@NonNull modelClass: Class<T>): T {
        return if (modelClass == PicsViewModel::class.java) {
            PicsViewModel(act) as T
        } else modelClass as T
    }
}