package com.example.scrollinggallery.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.scrollinggallery.R

class MainActivity : AppCompatActivity() {

    var itemBack: MenuItem? = null
    var itemFavorites: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?:let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrameContainer, PicsFragment().newInstance(), "MAIN_FRAGMENT")
                .commit()
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
            }
            R.id.actionFavorites -> {
                item.isVisible = false
                itemBack?.isVisible = true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        reloadList()
        return true
    }

    private fun reloadList(){
        val articleFrag = supportFragmentManager.findFragmentByTag("MAIN_FRAGMENT") as PicsFragment
        articleFrag.changeRepositoryType()
    }
}