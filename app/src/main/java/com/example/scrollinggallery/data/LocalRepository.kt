package com.example.scrollinggallery.data

import com.example.scrollinggallery.AppController
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.domain.pageToIndex

class LocalRepository: PicsRepository {

    override suspend fun getList(page: Int): List<Pic>{
        val indexPair = pageToIndex(page)

        return PicturesMapper().listFromDatabase(
            AppController.database.getPage(indexPair.first, indexPair.second)
        )
    }
}