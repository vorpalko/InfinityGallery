package com.example.scrollinggallery.network

import com.example.scrollinggallery.network.data.PicsumDTO
import retrofit2.Call
import retrofit2.http.*

interface PicsumApi {

    @GET("/id/{id}/info")
    fun getPictureById(
        @Path("id") id: Int
    ): Call<PicsumDTO>

    @GET("v2/list")
    fun getPicturesByPage(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Call<List<PicsumDTO>>
}