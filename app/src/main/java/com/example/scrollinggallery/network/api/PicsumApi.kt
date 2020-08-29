package com.example.scrollinggallery.network.api

import com.example.scrollinggallery.network.data.PicsumDTO
import com.example.scrollinggallery.network.utils.*
import retrofit2.Call
import retrofit2.http.*

interface PicsumApi {

    @GET(PATH_GET_ID)
    fun getPictureById(
        @Path(QUERY_ID) id: Int
    ): Call<PicsumDTO>

    @GET(PATH_GET_LIST)
    fun getPicturesByPage(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_LIMIT) limit: Int
    ): Call<List<PicsumDTO>>
}