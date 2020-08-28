package com.example.scrollinggallery.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scrollinggallery.R

//0.6
//0.6 header

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