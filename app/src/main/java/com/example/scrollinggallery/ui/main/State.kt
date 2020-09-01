package com.example.scrollinggallery.ui.main

enum class DataType{
    LOCAL,
    REMOTE;

    fun swap(type :DataType) =
        if(type == LOCAL)
            REMOTE
        else
            LOCAL
}

enum class Status{
    SUCCESS,
    ERROR,
    LOADING
}