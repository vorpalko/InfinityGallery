package com.example.scrollinggallery.data

import com.example.scrollinggallery.AppController
import com.example.scrollinggallery.AppController.Companion.localIds
import com.example.scrollinggallery.data.db.PicsDao
import com.example.scrollinggallery.data.model.PictureEntity
import com.example.scrollinggallery.data.network.PicsumApi
import com.example.scrollinggallery.data.util.PAGE_SIZE
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.data.util.hasInLocalStorage
import com.example.scrollinggallery.domain.PicDetailed
import javax.inject.Inject

fun pageToIndex(page: Int): Pair<Int, Int> = Pair((page - 1) * PAGE_SIZE, PAGE_SIZE)

fun picToEntity(from: Pic) = PictureEntity(from.id, from.author, from.url)

fun entityToPic(from: PictureEntity) = Pic(from.picture_id, from.author, from.url)

fun listFromEntities(list: List<PictureEntity>?): List<Pic>{
    val newList = ArrayList<Pic>()
    list?.forEach {
        newList.add(entityToPic(it))
    }
    return newList
}

class LocalPicsRepository@Inject constructor(
            private val database: PicsDao
): PicsRepository {

    override suspend fun getList(page: Int): List<PicDetailed>{
        val detailedList = ArrayList<PicDetailed>()

        val indexPair = pageToIndex(page)
        val pageList = listFromEntities(database.getPage(indexPair.first, indexPair.second))

        pageList.forEach {
            it.isLiked = true

            detailedList.add(PicDetailed.newSuccessPic(it))
        }
        return detailedList
    }

    suspend fun addToDB(pic: Pic){
        val id = pic.id

        if(!hasInLocalStorage(id)){
            localIds.add(id)
            AppController.database.insert(picToEntity(pic))
        }
    }

    suspend fun removeFromDB(pic: Pic){
        val id = pic.id

        localIds.remove(id)
        AppController.database.delete(id)
    }
}