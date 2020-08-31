package com.example.scrollinggallery.data.network

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.example.scrollinggallery.data.util.CACHE_ENABLED
import me.jessyan.progressmanager.ProgressManager
import okhttp3.OkHttpClient
import java.io.InputStream

@GlideModule
class GlideConfig : AppGlideModule(){

    private fun getOkHttpClient(): OkHttpClient =
        ProgressManager.getInstance()
            .with(OkHttpClient.Builder())
            .build()

    override fun isManifestParsingEnabled() = false

    override fun applyOptions(context: Context, builder: GlideBuilder){
        val cacheStrategy =
            if (CACHE_ENABLED)
                DiskCacheStrategy.RESOURCE
                //DiskCacheStrategy.ALL
            else
                DiskCacheStrategy.NONE

        builder.setDefaultRequestOptions(
            RequestOptions()
                .centerCrop()
                .diskCacheStrategy(cacheStrategy)
        )
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(getOkHttpClient())
        )
    }
}