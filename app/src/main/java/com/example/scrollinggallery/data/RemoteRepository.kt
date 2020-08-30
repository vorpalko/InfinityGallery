package com.example.scrollinggallery.data

import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.network.ApiFactory
import com.example.scrollinggallery.network.utils.PAGE_SIZE

class RemoteRepository : PicsRepository {

    override suspend fun getList(page: Int): List<Pic>{
        return PicturesMapper().listFromNetwork(
            ApiFactory.picsumApi.getPicturesByPage(page, PAGE_SIZE))
    }
}