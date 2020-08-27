package com.example.scrollinggallery.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scrollinggallery.R

//Initial key в Paging Library позволяет вернуться в часть списка, где последний раз был юзер
//placeholders
//
//https://www.raywenderlich.com/6948-paging-library-for-android-with-kotlin-creating-infinite-lists
// room can create datasource
//todo:
//
//добавить видимую пользователю
//обработку ошибок: noInternetConnection когда невозможно залоадить дату в ресайклер или обновить его
//
//
//

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?:let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrameContainer, RecyclerFragment().newInstance())
                .commit()
        }
    }
}