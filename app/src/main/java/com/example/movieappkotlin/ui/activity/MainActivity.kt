package com.example.movieappkotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.movieappkotlin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val navHostFragment= supportFragmentManager.findFragmentById(R.id.fragment_nav_host_home) as NavHostFragment
        val navController=navHostFragment.navController
    }
}