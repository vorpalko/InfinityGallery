package com.example.scrollinggallery.network.api

import com.example.scrollinggallery.data.model.ResponseDTO
import com.example.scrollinggallery.network.utils.*
import retrofit2.Call
import retrofit2.http.*

interface PicsumApi {

    @GET(PATH_GET_ID)
    suspend fun getPictureById(
        @Path(QUERY_ID) id: Int
    ): ResponseDTO

    @GET(PATH_GET_LIST)
    suspend fun getPicturesByPage(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_LIMIT) limit: Int
    ): List<ResponseDTO>
}