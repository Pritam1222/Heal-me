package com.example.heal_me.fragments

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.heal_me.HomeNavControl
import com.example.heal_me.LoginActivity
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentPhoneNoBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneNoFragment : Fragment() {
    private var _binding: FragmentPhoneNoBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth : FirebaseAuth
    private lateinit var navController : NavController
    private lateinit var fullNumber : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPhoneNoBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()

        val navHostFragment = (context as FragmentActivity).supportFragmentManager.findFragmentById(R.id.nav_login_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.phoneNoProgressBar.visibility = View.INVISIBLE

        // Check if location permissions are granted
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request location permissions
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                101
            )
        }

        sharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val conditionText = SpannableStringBuilder(binding.conditionText.text.toString())
        val fColor = ForegroundColorSpan(Color.rgb(8, 155, 171))
        conditionText.setSpan(fColor,35,42, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.conditionText.text = conditionText

        val termsAndConditions = SpannableString(binding.termsConditions.text.toString())
        val underlineSpan = UnderlineSpan()
        termsAndConditions.setSpan(underlineSpan,0,16, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        binding.termsConditions.text = termsAndConditions
        binding.termsConditions.setOnClickListener {
            it.findNavController().navigate(R.id.action_phoneNoFragment_to_termsConditionsFragment)
        }


        val editor = sharedPreferences.edit()

        editor.putString("wallet_cash", (50000).toDouble().toString())

        binding.phoneNo.setOnClickListener {
            fullNumber = binding.editTextNumber.text.trim().toString()
            Log.i("Phone No Fragment", fullNumber)

            if(fullNumber.isNotEmpty()){
                if (fullNumber.length == 10){

                    fullNumber = "+91$fullNumber"
                    binding.phoneNoProgressBar.visibility = View.VISIBLE
                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(fullNumber) // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(requireActivity()) // Activity (for callback binding)
                        .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)

                    // Save the name and dob to SharedPreferences
                    editor.putString("mob_num", fullNumber)
                    editor.apply()

                } else{
                    Toast.makeText(activity, "Invalid Phone number", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(activity, "Please enter Phone number", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(requireContext(),"Authenticate Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(), HomeNavControl::class.java))
                    requireActivity().finish()
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.d("TAG","signInWithPhoneAuthCredential: ${task.exception.toString()}")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e)

            when (e) {
                is FirebaseAuthInvalidCredentialsException -> {
                    // Invalid request
                    Log.d("TAG","onVerificationFailed: ${e.toString()}")
                }

                is FirebaseTooManyRequestsException -> {
                    // The SMS quota for the project has been exceeded
                    Log.d("TAG","onVerificationFailed: ${e.toString()}")
                }

                is FirebaseAuthMissingActivityForRecaptchaException -> {
                    // reCAPTCHA verification attempted with null Activity
                    Log.d("TAG","onVerificationFailed: ${e.toString()}")
                }
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            // Save verification ID and resending token so we can use them later
            val action =
                PhoneNoFragmentDirections.actionPhoneNoFragmentToOtpFragment(verificationId,token,fullNumber)
            navController.navigate(action)
            println("Verification ID: $verificationId")
            println("Resending Token: $token")
            println("Full Phone Number: $fullNumber")
            binding.phoneNoProgressBar.visibility = View.INVISIBLE

        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            val intent = Intent(requireContext(), HomeNavControl::class.java)
            startActivity(intent)
            requireActivity().finish() // Optional: Finish the current activity if you don't want to return to it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}