package com.example.heal_me.fragments

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.*
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.heal_me.HomeNavControl
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentOtpBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class OtpFragment : Fragment() {

    private lateinit var fragmentOtpBinding: FragmentOtpBinding

    private lateinit var navController : NavController
    private val args: OtpFragmentArgs by navArgs()

    private lateinit var OTP : String
    private lateinit var resendToken : PhoneAuthProvider.ForceResendingToken
    private lateinit var phoneNumber : String
    private lateinit var auth : FirebaseAuth
    private var enteredOtp: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentOtpBinding = FragmentOtpBinding.inflate(layoutInflater)

        OTP = args.verificationId
        resendToken = args.token
        phoneNumber = args.fullNumber
        auth = FirebaseAuth.getInstance()

        val navHostFragment = (context as FragmentActivity).supportFragmentManager.findFragmentById(R.id.nav_login_fragment) as NavHostFragment
        navController = navHostFragment.navController

        fragmentOtpBinding.otpProgressBar.visibility = View.INVISIBLE

        val conditionText = SpannableStringBuilder(fragmentOtpBinding.conditionText.text.toString())
        val fColor = ForegroundColorSpan(Color.rgb(8, 155, 171))
        conditionText.setSpan(fColor,35,42, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        fragmentOtpBinding.conditionText.text = conditionText

        val termsAndConditions = SpannableString(fragmentOtpBinding.termsConditions.text.toString())
        val underlineSpan = UnderlineSpan()
        termsAndConditions.setSpan(underlineSpan,0,16, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        fragmentOtpBinding.termsConditions.text = termsAndConditions
        fragmentOtpBinding.termsConditions.setOnClickListener {
            it.findNavController().navigate(R.id.action_otpFragment_to_termsConditionsFragment)
        }

        val pinView = fragmentOtpBinding.pinView

//        pinView.requestFocus()
//        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY)

        pinView.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(charSequence:CharSequence, start:Int, before:Int, count:Int) {
                enteredOtp = charSequence.toString()

            }
            override fun beforeTextChanged(charSequence:CharSequence, start:Int, count:Int,
                                           after:Int) {
                // TODO Auto-generated method stub
            }
            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })

        resendOtpVisibility()

        fragmentOtpBinding.resendOtp.setOnClickListener {
            resendVerificationCode()
            resendOtpVisibility()
        }

        fragmentOtpBinding.otp.setOnClickListener {
            if (enteredOtp.isNotEmpty()){
                if (enteredOtp.length==6)
                {
                    val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(
                        OTP,enteredOtp
                    )
                    fragmentOtpBinding.otpProgressBar.visibility = View.VISIBLE
                    signInWithPhoneAuthCredential(credential)

                }else{
                    Toast.makeText(activity, "Invalid OTP", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(activity, "Please enter OTP", Toast.LENGTH_SHORT).show()
            }

        }

        return fragmentOtpBinding.root
    }

    private fun resendOtpVisibility(){
        fragmentOtpBinding.resendOtp.visibility = View.INVISIBLE
        fragmentOtpBinding.resendOtpDesc.visibility = View.INVISIBLE

        fragmentOtpBinding.resendOtp.isEnabled = false
        fragmentOtpBinding.resendOtpDesc.isEnabled = false

        Handler(Looper.myLooper()!!).postDelayed({
            fragmentOtpBinding.resendOtp.visibility = View.VISIBLE
            fragmentOtpBinding.resendOtpDesc.visibility = View.VISIBLE

            fragmentOtpBinding.resendOtp.isEnabled = true
            fragmentOtpBinding.resendOtpDesc.isEnabled = true

        },5000)
    }

    private fun resendVerificationCode(){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity()) // Activity (for callback fragmentOtpBinding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .setForceResendingToken(resendToken)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

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
            Log.w(ContentValues.TAG, "onVerificationFailed", e)

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
            OTP = verificationId
            resendToken = token

        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    fragmentOtpBinding.otpProgressBar.visibility = View.VISIBLE
                    Toast.makeText(requireContext(),"Authenticate Successfully", Toast.LENGTH_SHORT).show()
                    val action =
                        OtpFragmentDirections.actionOtpFragmentToInfoNameFragment()
                    navController.navigate(action)
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
}