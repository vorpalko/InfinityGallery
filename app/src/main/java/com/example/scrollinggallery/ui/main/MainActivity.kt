package com.example.scrollinggallery.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scrollinggallery.R

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