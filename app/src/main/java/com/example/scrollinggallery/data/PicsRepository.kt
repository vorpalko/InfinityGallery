package com.example.scrollinggallery.data

import com.example.scrollinggallery.domain.PicDetailed

interface PicsRepository {

    suspend fun getList(page: Int): List<PicDetailed>

    //fun getById(id: Int): Pic

    //fun insert(pic: Pic)

    //fun insertList(pics: List<Pic>)

}