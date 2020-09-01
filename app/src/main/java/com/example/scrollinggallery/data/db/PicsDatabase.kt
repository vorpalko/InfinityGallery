package com.example.scrollinggallery.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.scrollinggallery.data.model.PictureEntity

@Database(entities = [PictureEntity::class], version = 38, exportSchema = false)
abstract class PicsDatabase : RoomDatabase() {
    abstract fun picsDao() : PicsDao
}