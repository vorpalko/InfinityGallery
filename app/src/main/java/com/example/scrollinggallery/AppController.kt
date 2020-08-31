package com.example.scrollinggallery

import android.app.Application
import androidx.room.Room
import com.example.scrollinggallery.data.db.PicsDao
import com.example.scrollinggallery.data.db.PicsDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import timber.log.Timber.DebugTree

class AppController: Application() {

    companion object{
        lateinit var database: PicsDao
        lateinit var localIds: ArrayList<Int>
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(DebugTree())

        database = Room.databaseBuilder(baseContext, PicsDatabase::class.java, "pics.db")
            .fallbackToDestructiveMigration()
            .build().picsDao()

        GlobalScope.launch {
            localIds = ArrayList()
            database.getAll().forEach {
                localIds.add(it.picture_id)
            }
        }
    }
}