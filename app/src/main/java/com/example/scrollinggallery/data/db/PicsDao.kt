package com.example.scrollinggallery.data.db

import androidx.room.*
import com.example.scrollinggallery.data.model.PictureEntity

@Dao
interface PicsDao {

    @Query("SELECT * FROM PictureEntity WHERE id = :id")
    suspend fun getById(id: Int): PictureEntity

    @Query("SELECT * FROM PictureEntity LIMIT :to OFFSET :from")
    suspend fun getPage(from: Int, to: Int): List<PictureEntity>

    @Query("SELECT * FROM PictureEntity")
    suspend fun getAll(): List<PictureEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pic: PictureEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(pics: List<PictureEntity>)

    @Query("DELETE FROM PictureEntity WHERE picture_id = :id")
    suspend fun delete(id: Int)
}