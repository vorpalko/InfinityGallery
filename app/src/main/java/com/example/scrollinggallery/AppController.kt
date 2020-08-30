package com.example.scrollinggallery

import android.app.Application
import androidx.room.Room
import com.example.scrollinggallery.data.db.PicsDao
import com.example.scrollinggallery.data.db.PicsDatabase
import com.example.scrollinggallery.ui.DB_NAME
import timber.log.Timber
import timber.log.Timber.DebugTree

class AppController: Application() {

    companion object{
        lateinit var database: PicsDao
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(baseContext, PicsDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build().picsDao()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}