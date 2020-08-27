package com.example.scrollinggallery.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PicsumDTO (
            @Json(name = "author") val author: String,
            @Json(name = "download_url") val download_url: String
)