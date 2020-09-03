package com.example.scrollinggallery.data

import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.data.util.hasInLocalStorage
import com.example.scrollinggallery.data.network.ApiFactory
import com.example.scrollinggallery.data.util.PAGE_SIZE
import com.example.scrollinggallery.data.util.PicturesMapper
import com.example.scrollinggallery.domain.PicDetailed

class RemoteRepository : PicsRepository {

    override suspend fun getList(page: Int): List<PicDetailed>{
        val detailedList = ArrayList<PicDetailed>()

        try {
            val pics = PicturesMapper().listFromNetwork(
                ApiFactory.picsumApi.getPicturesByPage(page, PAGE_SIZE))
            pics.forEach {
                it.isLiked = hasInLocalStorage(it.id)
                detailedList.add(PicDetailed.newSuccessPic(it))
            }
        }
        catch (e: Exception){
            detailedList.add(PicDetailed.newErrorPic())
        }
        return detailedList
    }
}