package com.example.scrollinggallery.data.util

import com.example.scrollinggallery.AppController


fun hasInLocalStorage(id: Int) = AppController.localIds.contains(id)

fun pageToIndex(page: Int): Pair<Int, Int>{
    val index = (page - 1) * PAGE_SIZE
    return Pair(index, PAGE_SIZE)
}

fun decremented(value: Int): Int{
    if(value > 1)
        return value - 1
    return value
}