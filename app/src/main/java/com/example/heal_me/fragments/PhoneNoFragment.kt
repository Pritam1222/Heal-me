package com.example.heal_me.fragments

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
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
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentPhoneNoBinding

class PhoneNoFragment : Fragment() {
    private var _binding: FragmentPhoneNoBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPhoneNoBinding.inflate(inflater, container, false)

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

        binding.phoneNo.setOnClickListener {
            val fullNumber = binding.editTextNumber.text.toString()


//            findNavController().navigate(R.id.action_phoneNoFragment_to_otpFragment)

            Log.i("Phone No Fragment", fullNumber)
            if (fullNumber.length == 10){

                // Save the name and dob to SharedPreferences
                editor.putString("mob_num", fullNumber)
                editor.apply()

                findNavController().navigate(R.id.action_phoneNoFragment_to_otpFragment)
            } else{
                Toast.makeText(activity, "Invalid Phone number", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}