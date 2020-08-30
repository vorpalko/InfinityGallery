package com.example.scrollinggallery.domain

interface PicsRepository {

    suspend fun getList(page: Int): List<Pic>

    //fun insert(pic: Pic)

    //fun insertList(pics: List<Pic>)

    //fun getById(id: Int): Pic
}