package com.example.scrollinggallery.data

import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.domain.hasInLocalStorage
import com.example.scrollinggallery.data.network.ApiFactory
import com.example.scrollinggallery.data.network.PAGE_SIZE

class RemoteRepository : PicsRepository {

    override suspend fun getList(page: Int): List<Pic>{
        val pics = PicturesMapper().listFromNetwork(
            ApiFactory.picsumApi.getPicturesByPage(page, PAGE_SIZE))

        pics.forEach {
            it.isLiked = hasInLocalStorage(it.id)
        }

        return pics
    }
}