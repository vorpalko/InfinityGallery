package com.example.scrollinggallery.ui.main

import com.example.scrollinggallery.domain.Pic

interface PicItemCallback {
    fun savePicCallback(pic: Pic)
    fun deletePicCallback(pic: Pic)
}