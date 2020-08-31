package com.example.scrollinggallery.data

import com.example.scrollinggallery.domain.Pic

interface PicsRepository {

    suspend fun getList(page: Int): List<Pic>

    //fun getById(id: Int): Pic

    //fun insert(pic: Pic)

    //fun insertList(pics: List<Pic>)

}