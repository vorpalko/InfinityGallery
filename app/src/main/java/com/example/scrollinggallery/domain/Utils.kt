package com.example.scrollinggallery.domain

import com.example.scrollinggallery.network.utils.PAGE_SIZE


fun pageToIndex(page: Int): Pair<Int, Int>{
    val index = (page - 1) * PAGE_SIZE
    return Pair(index, PAGE_SIZE)
}

fun decremented(value: Int): Int{
    if(value > 1)
        return value - 1
    return value
}