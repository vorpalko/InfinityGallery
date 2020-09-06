package com.example.scrollinggallery.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.scrollinggallery.R
import com.example.scrollinggallery.ui.main.local.LocalStorageFragment
import com.example.scrollinggallery.ui.main.remote.RemoteStorageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var itemBack: MenuItem? = null
    private var itemFavorites: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?:let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrameContainer, RemoteStorageFragment.newInstance()).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)

        itemFavorites = menu!!.findItem(R.id.actionFavorites)
        itemBack = menu.findItem(R.id.actionBack)
        itemBack?.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionBack -> {
                item.isVisible = false
                itemFavorites?.isVisible = true

                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainFrameContainer, RemoteStorageFragment.newInstance()).commit()
            }
            R.id.actionFavorites -> {
                item.isVisible = false
                itemBack?.isVisible = true

                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainFrameContainer, LocalStorageFragment.newInstance()).commit()
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return true
    }
}