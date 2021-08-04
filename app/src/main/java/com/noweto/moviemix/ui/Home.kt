package com.noweto.moviemix.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.noweto.moviemix.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*


/**
 *  Activity host Navigation and bottom nav ..
 */

@AndroidEntryPoint
class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        setUpBottomNav()

    }

    private fun setUpBottomNav(){

        bottom_nav.itemIconTintList = null
        val navController = Navigation.findNavController(this,R.id.nav_host)
        bottom_nav.setupWithNavController(navController)

    }



}