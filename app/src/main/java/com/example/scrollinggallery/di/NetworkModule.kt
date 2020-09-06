package com.example.scrollinggallery.di

import com.example.scrollinggallery.data.network.PicsumApi
import com.example.scrollinggallery.data.util.API_ENDPOINT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun provideNetworkDataSource(retrofit: Retrofit): PicsumApi =
        retrofit.create(PicsumApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofitClient(okHttpClient: OkHttpClient, baseUrl: String, converterFactory: Converter.Factory): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): Converter.Factory = MoshiConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideBaseUrl(): String = API_ENDPOINT
}