package com.example.scrollinggallery.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PicsumDTO (
            @Json(name = "id") val id: Int,
            @Json(name = "author") val author: String,
            @Json(name = "download_url") val url: String
)