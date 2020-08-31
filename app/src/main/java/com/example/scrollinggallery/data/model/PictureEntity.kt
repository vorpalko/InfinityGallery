package com.example.scrollinggallery.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PictureEntity(
    @ColumnInfo val picture_id : Int,
    @ColumnInfo val author : String,
    @ColumnInfo val url : String,
){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}