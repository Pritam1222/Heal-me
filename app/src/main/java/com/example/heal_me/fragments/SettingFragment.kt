@file:Suppress("OverrideDeprecatedMigration", "OverrideDeprecatedMigration")

package com.example.heal_me.fragments

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.heal_me.HomeNavControl
import com.example.heal_me.LoginActivity
import com.example.heal_me.R
import com.example.heal_me.SplashScreenActivity
import com.example.heal_me.data.Appointment
import com.example.heal_me.databinding.FragmentSettingBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
@Suppress("OverrideDeprecatedMigration", "OverrideDeprecatedMigration")
class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth : FirebaseAuth

    private val GALLERY_REQUEST_CODE = 103
    private val IMAGE_FILENAME = "profile_image.jpg"
    private var imageBitmap: Bitmap? = null
    private var imageUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()
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

        binding.settingsNotification.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment_to_notificationFragment)

            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
                View.GONE
        }


        binding.settingsMyAppointment.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment_to_myAppointmentFragment)

            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
                View.GONE
        }


        binding.settingsFavouriteDoctors.setOnClickListener{
            it.findNavController().navigate(R.id.action_settingFragment_to_favouriteDoctorFragment)

            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
                View.GONE
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

        binding.settingLogout.setOnClickListener {

            showCustomDialog(requireContext())

            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
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

    private fun showCustomDialog(context: Context) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.fragment_logout_dialog)

        val userName = sharedPreferences.getString("name", "")
        val tvUserName = dialog.findViewById<TextView>(R.id.user_name)

        tvUserName.text = userName.toString()

        val dialogOKButton = dialog.findViewById<Button>(R.id.bu_logout)
        dialogOKButton.setOnClickListener {

//             Handle the "OK" button click
            val editor = sharedPreferences.edit()
            editor.putString("mob_num", "")
            editor.putString("name", "")
            editor.putString("dob", "")
            editor.putString("email", "")
            editor.putString("gender", "")
            editor.apply()

            auth.signOut()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
            dialog.dismiss()
        }

        val dialogCancelButton = dialog.findViewById<Button>(R.id.bu_cancel)
        dialogCancelButton.setOnClickListener {
            // Handle the "Cancel" button click
            dialog.dismiss()
        }

        dialog.show()
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
                binding.ivSettingProfile.setImageBitmap(imageBitmap)
                // Save the image
                imageBitmap?.let { saveImage(it) }
                // adding imageUri into the shared preferences
                editor.putString("profile_image", imageUri.toString()).apply()
            }
        }
    }


}