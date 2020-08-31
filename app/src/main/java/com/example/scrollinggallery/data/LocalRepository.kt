package com.example.scrollinggallery.data

import com.example.scrollinggallery.AppController
import com.example.scrollinggallery.AppController.Companion.localIds
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.domain.pageToIndex
import timber.log.Timber


fun hasInLocalStorage(id: Int) = localIds.contains(id)

class LocalRepository: PicsRepository {

    override suspend fun getList(page: Int): List<Pic>{
        val indexPair = pageToIndex(page)
        val pageList = PicturesMapper().listFromDatabase(
            AppController.database.getPage(indexPair.first, indexPair.second))

        pageList.forEach {
            it.isLiked = true
        }
        return pageList
    }

    suspend fun addToDB(pic: Pic){
        val id = pic.id

        if(!hasInLocalStorage(id)){
            localIds.add(id)
            AppController.database.insert(
                PicturesMapper().toDatabase(pic))
        }
    }

    suspend fun removeFromDB(pic: Pic){
        val id = pic.id

        localIds.remove(id)
        AppController.database.delete(id)
    }
}