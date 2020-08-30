package com.example.scrollinggallery.data.db

import androidx.room.*
import com.example.scrollinggallery.data.model.PictureEntity
import org.jetbrains.annotations.NotNull

@Dao
interface PicsDao {

    @Query("SELECT * FROM PictureEntity WHERE id = :id")
    suspend fun getById(id: Int): PictureEntity

    @Query("SELECT * FROM PictureEntity WHERE id BETWEEN :from AND :to")
    suspend fun getPage(from: Int, to: Int): List<PictureEntity>

    @Query("SELECT * FROM PictureEntity")
    suspend fun getAll(): List<PictureEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pic: PictureEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(pics: List<PictureEntity>)

    @Delete
    suspend fun delete(pic: PictureEntity)
}