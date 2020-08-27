package com.example.scrollinggallery.network.api

import com.example.scrollinggallery.network.data.PicsumDTO
import com.example.scrollinggallery.network.util.PATH_GET_ID
import com.example.scrollinggallery.network.util.PATH_GET_LIST
import retrofit2.Call
import retrofit2.http.*

interface PicsumApi {

    @GET(PATH_GET_ID)
    fun getPictureById(
        @Path("id") id: Int
    ): Call<PicsumDTO>

    @GET(PATH_GET_LIST)
    fun getPicturesByPage(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Call<List<PicsumDTO>>
}