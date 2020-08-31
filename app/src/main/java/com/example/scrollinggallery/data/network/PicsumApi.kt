package com.example.scrollinggallery.data.network

import com.example.scrollinggallery.data.model.ResponseDTO
import retrofit2.http.*

interface PicsumApi {

    @GET(PATH_GET_LIST)
    suspend fun getPicturesByPage(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_LIMIT) limit: Int
    ): List<ResponseDTO>
}