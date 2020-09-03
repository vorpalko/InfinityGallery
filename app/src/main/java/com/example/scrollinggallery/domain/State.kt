package com.example.scrollinggallery.domain

enum class Status{
    SUCCESS,
    ERROR
}

enum class DataType{
    LOCAL,
    REMOTE;

    fun swap(type : DataType) =
        if(type == LOCAL)
            REMOTE
        else
            LOCAL
}