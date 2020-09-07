package com.example.scrollinggallery

import android.app.Application
import com.example.scrollinggallery.data.LocalPicsRepository.Companion.localIds
import com.example.scrollinggallery.di.DatabaseModule
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class AppController: Application() {

    override fun onCreate() {
        super.onCreate()

        initLikedPics()
        if (BuildConfig.DEBUG)
            Timber.plant(DebugTree())
    }

    private fun initLikedPics(){
        GlobalScope.launch {
            DatabaseModule.provideLocalDataSource(applicationContext).getAll()
                .forEach {
                    localIds.add(it.picture_id)
                }
        }
    }
}