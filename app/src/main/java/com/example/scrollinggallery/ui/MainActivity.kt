package com.example.scrollinggallery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scrollinggallery.R
import com.example.scrollinggallery.RecyclerFragment

//Initial key в Paging Library позволяет вернуться в часть списка, где последний раз был юзер
//placeholders
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