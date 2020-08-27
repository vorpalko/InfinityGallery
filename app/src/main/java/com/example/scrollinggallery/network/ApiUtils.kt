package com.example.scrollinggallery.network

import com.example.scrollinggallery.BuildConfig
import com.example.scrollinggallery.network.api.PicsumApi
import com.example.scrollinggallery.network.util.API_ENDPOINT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiUtils {

    private var api: PicsumApi? = null

    fun getPicsumApi(): PicsumApi? {
        if(api == null){
            api = createRetrofitClient().create(PicsumApi::class.java)
        }
        return api
    }

    private fun createRetrofitClient() =
        Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .client(createBasicClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    private fun createBasicClient() =
        OkHttpClient.Builder()
            .addInterceptor(createInterceptor())
            .build()

    private fun createInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}