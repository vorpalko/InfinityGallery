package com.example.scrollinggallery.data.api

import com.example.scrollinggallery.data.model.ResponseDTO
import com.example.scrollinggallery.data.util.PATH_GET_LIST
import com.example.scrollinggallery.data.util.QUERY_LIMIT
import com.example.scrollinggallery.data.util.QUERY_PAGE
import retrofit2.http.*

interface PicsumApi {

    @GET(PATH_GET_LIST)
    suspend fun getPicturesByPage(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_LIMIT) limit: Int
    ): List<ResponseDTO>
}