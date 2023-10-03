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
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.heal_me.R
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class AccountFragment : Fragment() {

    private lateinit var fragmentAccountBinding: FragmentAccountBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val GALLERY_REQUEST_CODE = 103
    private val IMAGE_FILENAME = "profile_image.jpg"
    private var imageBitmap: Bitmap? = null
    private var imageUri: Uri? = null

    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAccountBinding = FragmentAccountBinding.inflate(layoutInflater)

        val navHostFragment = (context as FragmentActivity).supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        sharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val mobNum = sharedPreferences.getString("mob_num", "")
        val fullName = sharedPreferences.getString("name", "")
        val dateOfBirth = sharedPreferences.getString("dob", "")
        val email = sharedPreferences.getString("email", "")
        val gender = sharedPreferences.getString("gender", "")
        val height = sharedPreferences.getString("height", "")
        val weight = sharedPreferences.getString("weight", "")
        val address = sharedPreferences.getString("address", "")
        val profileImageUri = sharedPreferences.getString("profile_image", "")

        fragmentAccountBinding.mobNum.text = mobNum.toString()
        fragmentAccountBinding.name.text = fullName.toString()
        fragmentAccountBinding.accountName.text = fullName.toString()
        fragmentAccountBinding.accountDob.text = dateOfBirth.toString()
        fragmentAccountBinding.accountEmail.text = email.toString()
        fragmentAccountBinding.accountGender.text = gender.toString()
        fragmentAccountBinding.height.text = height.toString()
        fragmentAccountBinding.weight.text = weight.toString()
        fragmentAccountBinding.tvAccountAddress.text = address.toString()

// Load and set the image if it exists
        val savedImageBitmap = loadSavedImage()
        if (savedImageBitmap != null) {
            fragmentAccountBinding.ivAccountProfile.setImageBitmap(savedImageBitmap)
            imageBitmap = savedImageBitmap
        } else if (imageUri != null) {
            Glide.with(this)
                .load(imageUri)
                .into(fragmentAccountBinding.ivAccountProfile)
        }

        fragmentAccountBinding.profileBackground.setOnClickListener {
            openGallery()
        }

        fragmentAccountBinding.ivAccountEdit.setOnClickListener {
            val action =
                AccountFragmentDirections.actionAccountFragmentToAccountEditFragment()
            navController.navigate(action)

            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
        }

        return fragmentAccountBinding.root
    }

    private fun saveImage(imageBitmap: Bitmap) {
        val imageFile = File(requireContext().filesDir, IMAGE_FILENAME)
        try {
            val fos = FileOutputStream(imageFile)
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun loadSavedImage(): Bitmap? {
        val imageFile = File(requireContext().filesDir, IMAGE_FILENAME)
        if (imageFile.exists()) {
            return BitmapFactory.decodeFile(imageFile.absolutePath)
        }
        return null
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val editor = sharedPreferences.edit()
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            if (imageUri != null) {
                this.imageUri = imageUri // Assign the obtained Uri to the class variable
                val imageBitmap = MediaStore.Images.Media.getBitmap(
                    requireContext().contentResolver,
                    imageUri
                )
                fragmentAccountBinding.ivAccountProfile.setImageBitmap(imageBitmap)
                imageBitmap?.let { saveImage(it) }
                editor.putString("profile_image", imageUri.toString()).apply()
            }
        }
    }

}