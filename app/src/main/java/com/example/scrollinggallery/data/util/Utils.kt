package com.example.scrollinggallery.data.util

import com.example.scrollinggallery.AppController

fun hasInLocalStorage(id: Int) = AppController.localIds.contains(id)