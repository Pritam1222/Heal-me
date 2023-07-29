package com.example.heal_me.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.*
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentOtpBinding

class OtpFragment : Fragment() {

    private var _binding: FragmentOtpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOtpBinding.inflate(inflater, container, false)

        val conditionText = SpannableStringBuilder(binding.conditionText.text.toString())
        val fColor = ForegroundColorSpan(Color.rgb(8, 155, 171))
        conditionText.setSpan(fColor,35,42, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.conditionText.text = conditionText

        val termsAndConditions = SpannableString(binding.termsConditions.text.toString())
        val underlineSpan = UnderlineSpan()
        termsAndConditions.setSpan(underlineSpan,0,16, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        binding.termsConditions.text = termsAndConditions
        binding.termsConditions.setOnClickListener {
            it.findNavController().navigate(R.id.action_otpFragment_to_termsConditionsFragment)
        }

        val pinView = binding.pinView

//        pinView.requestFocus()
//        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY)

        pinView.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(charSequence:CharSequence, start:Int, before:Int, count:Int) {
                binding.otp.setOnClickListener {
                    findNavController().navigate(R.id.action_otpFragment_to_infoNameFragment)
//                    if (charSequence.toString()=="1234")
//                    {
//                        findNavController().navigate(R.id.action_otpFragment_to_infoNameFragment)
//                    }else{
//                        Toast.makeText(activity, "Invalid OTP", Toast.LENGTH_SHORT).show()
//                    }
                }

            }
            override fun beforeTextChanged(charSequence:CharSequence, start:Int, count:Int,
                                           after:Int) {
                // TODO Auto-generated method stub
            }
            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })


//        binding.otp.setOnClickListener {
//            findNavController().navigate(R.id.action_otpFragment_to_infoNameFragment)
//
//        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}