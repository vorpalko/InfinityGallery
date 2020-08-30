package com.example.scrollinggallery.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDTO (
            @Json(name = "id") val id: Int,
            @Json(name = "author") val author: String,
            @Json(name = "download_url") val url: String
)