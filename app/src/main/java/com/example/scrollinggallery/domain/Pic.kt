package com.example.scrollinggallery.domain

data class Pic(
    val id: Int,
    val author: String,
    val url: String,
    var isLiked: Boolean = false
)