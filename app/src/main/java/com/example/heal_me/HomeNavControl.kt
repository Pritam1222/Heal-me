package com.example.heal_me

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.heal_me.databinding.HomeNavControlBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeNavControl : AppCompatActivity() {

    private lateinit var binding: HomeNavControlBinding
    private lateinit var sharedViewModel: AppointmentSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeNavControlBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Initialize the shared ViewModel
        sharedViewModel = ViewModelProvider(this).get(AppointmentSharedViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.setGraph(R.navigation.nav_home_control_graph)
        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.doctorsFragment -> binding.bottomNavigation.visibility = View.GONE
                R.id.symptomsFragment -> binding.bottomNavigation.visibility = View.GONE
                R.id.settingFragment -> binding.bottomNavigation.visibility = View.GONE
                R.id.walletFragment -> binding.bottomNavigation.visibility = View.GONE
                else -> binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
    }
}