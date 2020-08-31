package com.example.scrollinggallery.data.util

import com.example.scrollinggallery.data.model.PictureEntity
import com.example.scrollinggallery.data.model.ResponseDTO
import com.example.scrollinggallery.domain.Pic

class PicturesMapper{

    fun toDatabase(from: Pic) = PictureEntity(from.id, from.author, from.url)

    fun listFromNetwork(list: List<ResponseDTO>): List<Pic>{
        val newList = ArrayList<Pic>()
        list.forEach {
            newList.add(fromNetwork(it))
        }
        return newList
    }

    fun listFromDatabase(list: List<PictureEntity>?): List<Pic>{
        val newList = ArrayList<Pic>()
        list?.forEach {
            newList.add(fromDatabase(it))
        }
        return newList
    }

    private fun fromNetwork(from: ResponseDTO) = Pic(from.id, from.author, from.url)

    private fun fromDatabase(from: PictureEntity) = Pic(from.picture_id, from.author, from.url)
}