package com.example.heal_me.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.heal_me.R
import com.example.heal_me.SharedViewModel
import com.example.heal_me.databinding.FragmentSettingBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    private val GALLERY_REQUEST_CODE = 103
    private val IMAGE_FILENAME = "profile_image.jpg"
    private var imageBitmap: Bitmap? = null
    private var imageUri: Uri? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)

        sharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val mobNum = sharedPreferences.getString("mob_num", "")
        val fullName = sharedPreferences.getString("name", "")

        binding.mobNum.text = mobNum.toString()
        binding.name.text = fullName.toString()

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbarSetting = binding.toolbarSetting
        toolbarSetting.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbarSetting)

        toolbarSetting.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.settingsTermsConditions.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment_to_termsConditionsFragment2)

            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
                View.GONE
        }

        binding.referApp.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment_to_referFragment)

            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
                View.GONE
        }

        binding.settingLogout.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment_to_logoutDialogFragment)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
                View.GONE
        }

        binding.settingsMyRewards.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment_to_rewardsFragment)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
                View.GONE
        }

        binding.settingPrivacyPolicy.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment_to_privacyPolicyFragment)

            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
                View.GONE
        }

        binding.settingHelpSupport.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment_to_helpSupportFragment)

            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
                View.GONE
        }

        binding.settingUpdateApp.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment_to_updateAppFragment)

            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
                View.GONE
        }

        // Load and set the image if it exists
        val savedImageBitmap = loadSavedImage()
        if (savedImageBitmap != null) {
            binding.ivSettingProfile.setImageBitmap(savedImageBitmap)
            imageBitmap = savedImageBitmap
        }


        binding.profileBackground.setOnClickListener {
            openGallery()
        }


        return binding.root
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
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            if (imageUri != null) {
                this.imageUri = imageUri // Assign the obtained Uri to the class variable
                val imageBitmap = MediaStore.Images.Media.getBitmap(
                    requireContext().contentResolver,
                    imageUri
                )
                binding.ivSettingProfile.setImageBitmap(imageBitmap)
                // Save the image
                imageBitmap?.let { saveImage(it) }
                // Set the imageUri in the shared ViewModel
                val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
                sharedViewModel.imageUri = imageUri.toString()
            }
        }
    }


}