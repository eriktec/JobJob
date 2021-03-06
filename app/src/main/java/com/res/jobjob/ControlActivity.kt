package com.res.jobjob

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.navigation.NavigationView
import com.res.jobjob.common.base.BaseToolbar
import com.res.jobjob.databinding.ActivityControlBinding
import com.res.jobjob.databinding.HeaderNavigationDrawerBinding
import com.res.jobjob.model.data.User
import com.res.jobjob.vm.activity.ControllerViewModel

class ControlActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val viewModel: ControllerViewModel by lazy { ViewModelProvider(this)[ControllerViewModel::class.java] }
    private lateinit var binding: ActivityControlBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var headerBinding: HeaderNavigationDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_control)
        val bundle = intent.extras
        if (bundle != null) {
            val user = bundle.getSerializable("user") as User
            headerNav(user)
        }
        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, findViewById(R.id.drawer_layout))
        BaseToolbar.showToolbar(this, findViewById(R.id.toolbar), appBarConfiguration, navController, binding.navigationView)
        binding.navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        viewModel.crearToken()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("user", headerBinding.user)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        headerBinding.user = savedInstanceState.getSerializable("user") as User
    }

    private fun headerNav(user: User) {
        headerBinding = HeaderNavigationDrawerBinding.bind(binding.navigationView.getHeaderView(0))
        headerBinding.user = user
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) or super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_salir -> {
                viewModel.salir()
                salir()
            }
            else -> { if (!item.isChecked) item.onNavDestinationSelected(navController) }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun salir() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }
}