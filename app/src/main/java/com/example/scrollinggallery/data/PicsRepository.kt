package com.example.scrollinggallery.data

import com.example.scrollinggallery.domain.PicDetailed

interface PicsRepository {

    suspend fun getList(page: Int): List<PicDetailed>

}