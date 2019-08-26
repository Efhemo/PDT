package com.efhems.socialvue.ui.listhire

import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.efhems.socialvue.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class ListHireActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_hire)


        val btmNav: BottomNavigationView = findViewById(R.id.bottom_navigation)

        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)
        NavigationUI.setupWithNavController(btmNav, navController)

        appBarConfiguration = AppBarConfiguration.Builder(setOf(R.id.categoryFragment,
                R.id.marketFragment, R.id.feedsFragment, R.id.settingsFragment2)).build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.listProfFragment -> {
                    btmNav.visibility = GONE

                }
                else  -> btmNav.visibility = VISIBLE

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!, navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
