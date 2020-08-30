package com.example.scrollinggallery.data

import com.example.scrollinggallery.data.model.ResponseDTO
import com.example.scrollinggallery.domain.Pic

class PicturesMapper{

    private fun fromNetwork(from: ResponseDTO) = Pic(from.id, from.author, from.url)

    //fun fromDatabase(from: PictureEntity) = Pic(from.id, from.author, from.url)

    fun listFromNetwork(list: List<ResponseDTO>): List<Pic>{
        val newList = ArrayList<Pic>()
        list.forEach {
            newList.add(fromNetwork(it))
        }
        return newList
    }

    //fun listFromDatabase(list: List<PictureEntity>): List<Pic>{
    //    val newList = ArrayList<Pic>()
    //    list.forEach {
    //        newList.add(fromDatabase(it))
    //    }
    //    return newList
    //}

}