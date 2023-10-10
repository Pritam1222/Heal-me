package com.example.heal_me.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.heal_me.AppointmentSharedViewModel
import com.example.heal_me.R
import com.example.heal_me.adapters.AppointmentViewPagerAdapter
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.databinding.FragmentAppointmentBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Locale


class AppointmentFragment : Fragment() {
    private lateinit var fragmentAppointmentBinding: FragmentAppointmentBinding
    private val tabTitle = arrayListOf("Upcoming","Completed","Canceled")
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var doctorsDatabase: DoctorsDatabase
    private lateinit var sharedViewModel: AppointmentSharedViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAppointmentBinding = FragmentAppointmentBinding.inflate(layoutInflater)

        val navHostFragment = (context as FragmentActivity).supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        // Initialize the shared ViewModel
        sharedViewModel = ViewModelProvider(requireActivity()).get(AppointmentSharedViewModel::class.java)

        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())

        setUpTabLayoutWithViewPager()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fetchLocation()

        fragmentAppointmentBinding.setting.setOnClickListener {
            it.findNavController().navigate(R.id.action_appointmentFragment_to_settingFragment)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
        }

        // Observe changes to the selectedAppointmentId LiveData
        sharedViewModel.selectedAppointmentId.observe(viewLifecycleOwner) { appointmentId ->
            appointmentId?.let {
                // Navigate to the RescheduleAppointmentFragment when a new appointment ID is set
                val action = AppointmentFragmentDirections.actionAppointmentFragmentToRescheduleAppointmentFragment(it)
                navController.navigate(action)
                activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
                // Reset the selected appointment ID to prevent multiple navigations
                sharedViewModel.setSelectedAppointmentId(null)
            }
        }

        return fragmentAppointmentBinding.root

    }

    private fun fetchLocation() {
        val task = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                101
            )
            return
        }

        task.addOnSuccessListener { location ->
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude

                val geocoder = Geocoder(requireContext(), Locale.getDefault())
                val addresses = geocoder.getFromLocation(latitude, longitude, 1)

                if (addresses != null) {
                    val address = addresses[0].locality
                    fragmentAppointmentBinding.tvCurrentLocation.text = address.toString()
                }
            }
        }
    }

    private fun setUpTabLayoutWithViewPager() {
        fragmentAppointmentBinding.vpAppointmentViewPager2.adapter = AppointmentViewPagerAdapter(this)
        TabLayoutMediator(fragmentAppointmentBinding.tlAppointmentTabLayout, fragmentAppointmentBinding.vpAppointmentViewPager2){tab, position ->
            tab.text = tabTitle[position]
        }.attach()


        for(i in 0..3){
            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_title, null)
                    as TextView
            fragmentAppointmentBinding.tlAppointmentTabLayout.getTabAt(i)?.customView =textView
        }
    }


}