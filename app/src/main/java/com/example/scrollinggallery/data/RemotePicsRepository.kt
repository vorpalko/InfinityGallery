package com.example.scrollinggallery.data

import com.example.scrollinggallery.data.model.ResponseDTO
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.data.util.hasInLocalStorage
import com.example.scrollinggallery.data.network.PicsumApi
import com.example.scrollinggallery.data.util.PAGE_SIZE
import com.example.scrollinggallery.domain.PicDetailed
import javax.inject.Inject

fun listFromNetwork(list: List<ResponseDTO>): List<Pic>{
    val newList = ArrayList<Pic>()
    list.forEach {
        newList.add(fromNetwork(it))
    }
    return newList
}

fun fromNetwork(from: ResponseDTO) = Pic(from.id, from.author, from.url)


class RemotePicsRepository @Inject constructor(
            private val networkRepository: PicsumApi
): PicsRepository {

    override suspend fun getList(page: Int): List<PicDetailed>{
        val detailedList = ArrayList<PicDetailed>()

        try {
            val pics = listFromNetwork(networkRepository.getPicturesByPage(page, PAGE_SIZE))
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