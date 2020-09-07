package com.example.scrollinggallery.di

import android.content.Context
import androidx.room.Room
import com.example.scrollinggallery.data.db.PicsDao
import com.example.scrollinggallery.data.db.PicsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext context: Context): PicsDao =
        Room.databaseBuilder(context, PicsDatabase::class.java, "pics.db")
            .fallbackToDestructiveMigration()
            .build().picsDao()
}