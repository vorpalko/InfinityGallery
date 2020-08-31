package com.example.scrollinggallery.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.scrollinggallery.R


class MainActivity : AppCompatActivity() {

    var itemBack: MenuItem? = null
    var itemFavorites: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?:let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrameContainer, RecyclerFragment().newInstance())
                .commit()
        }
    }

    override fun onPause() {

        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)

        itemFavorites = menu!!.findItem(R.id.actionFavorites)
        itemBack = menu.findItem(R.id.actionBack)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.actionBack -> {
            item.isVisible = false
            true
        }
        R.id.actionFavorites -> {
            item.isVisible = false
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}