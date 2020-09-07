package com.example.scrollinggallery.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.scrollinggallery.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var menuItemBack: MenuItem
    private lateinit var menuItemFavorites: MenuItem

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.mainFragmentContainer) as NavHostFragment
        navController = host.navController
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        menuItemFavorites = menu.findItem(R.id.actionFavorites)
        menuItemBack = menu.findItem(R.id.actionBack)

        if(navController.currentDestination?.id == R.id.remoteStorageFragment){
            menuItemBack.isVisible = false
            menuItemFavorites.isVisible = true
        }
        else{
            menuItemBack.isVisible = true
            menuItemFavorites.isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionBack -> {
                item.isVisible = false
                menuItemFavorites.isVisible = true
                navController.navigate(R.id.remoteStorageFragment)
            }
            R.id.actionFavorites -> {
                item.isVisible = false
                menuItemBack.isVisible = true
                navController.navigate(R.id.localStorageFragment)
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    //поддержка бэкстека для локального репозитория
    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.localStorageFragment -> {
                menuItemFavorites.isVisible = true
                menuItemBack.isVisible = false
                navController.navigate(R.id.remoteStorageFragment)
            }
            R.id.remoteStorageFragment -> {
                finish()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}