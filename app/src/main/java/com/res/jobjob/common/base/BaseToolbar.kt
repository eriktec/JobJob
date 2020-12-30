package com.res.jobjob.common.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

object BaseToolbar {

    fun showToolbar(activity: AppCompatActivity, toolbar: Toolbar, navController: NavController) {
        activity.setSupportActionBar(toolbar)
        activity.setupActionBarWithNavController(navController)
    }

    fun showToolbar(activity: AppCompatActivity, toolbar: Toolbar, appBarConfiguration: AppBarConfiguration,
                    navController: NavController, navigationView: NavigationView) {
        activity.setSupportActionBar(toolbar)
        activity.setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
    }
}