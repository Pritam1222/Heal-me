package com.example.heal_me.fragments

import android.content.Context
import android.content.SharedPreferences
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.heal_me.databinding.FragmentAccountBinding
import com.google.android.gms.location.FusedLocationProviderClient
import java.util.Locale
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices


class AccountFragment : Fragment() {

    private lateinit var fragmentAccountBinding: FragmentAccountBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAccountBinding = FragmentAccountBinding.inflate(layoutInflater)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fetchLocation()

        sharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val mobNum = sharedPreferences.getString("mob_num", "")
        val fullName = sharedPreferences.getString("name", "")
        val dateOfBirth = sharedPreferences.getString("dob", "")
        val email = sharedPreferences.getString("email", "")
        val gender = sharedPreferences.getString("gender", "")
        val profileImageUri = sharedPreferences.getString("profile_image", "")

        fragmentAccountBinding.mobNum.text = mobNum.toString()
        fragmentAccountBinding.name.text = fullName.toString()
        fragmentAccountBinding.accountName.text = fullName.toString()
        fragmentAccountBinding.accountDob.text = dateOfBirth.toString()
        fragmentAccountBinding.accountEmail.text = email.toString()
        fragmentAccountBinding.accountGender.text = gender.toString()

        // Check if imageUri is not null
        Glide.with(this)
            .load(Uri.parse(profileImageUri))
            .into(fragmentAccountBinding.ivAccountProfile)

        return fragmentAccountBinding.root
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
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                arrayOf(
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                ),
//                101
//            )
            return
        }

        task.addOnSuccessListener { location ->
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude

                val geocoder = Geocoder(requireContext(), Locale.getDefault())
                val addresses = geocoder.getFromLocation(latitude, longitude, 1)

                if (addresses != null) {
                    val address = addresses[0].thoroughfare + ", " + addresses[0].subLocality + ", " + addresses[0].locality + ", " + addresses[0].subAdminArea + ", " + addresses[0].adminArea + ", " + addresses[0].countryName + ", " + addresses[0].postalCode
                    fragmentAccountBinding.tvAccountAddress.text = address.toString()
                }
            }
        }
    }

}