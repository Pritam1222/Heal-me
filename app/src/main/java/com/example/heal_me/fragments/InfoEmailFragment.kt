package com.example.heal_me.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.nfc.Tag
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
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.heal_me.HomeNavControl
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentInfoEmailBinding

class InfoEmailFragment : Fragment() {

    private var _binding: FragmentInfoEmailBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoEmailBinding.inflate(inflater, container, false)

        sharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val button: ImageButton = binding.gender
        val genderText = binding.genderText
        val genders = arrayOf("Male", "Female", "Other")
        var selected = -1

        button.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Select Gender")
            builder.setSingleChoiceItems(genders, selected) { _, which ->
                selected = which
            }
            builder.setPositiveButton("ok") { _, _ ->
                genderText.setText(genders[selected])
            }
            builder.setNegativeButton("Cancel"){dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }



        val conditionText = SpannableStringBuilder(binding.conditionText.text.toString())
        val fColor = ForegroundColorSpan(Color.rgb(8, 155, 171))
        conditionText.setSpan(fColor,35,42, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.conditionText.text = conditionText

        val termsAndConditions = SpannableString(binding.termsConditions.text.toString())
        val underlineSpan = UnderlineSpan()
        termsAndConditions.setSpan(underlineSpan,0,16, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        binding.termsConditions.text = termsAndConditions
        binding.termsConditions.setOnClickListener {
            it.findNavController().navigate(R.id.action_infoEmailFragment_to_termsConditionsFragment)
        }


        val editor = sharedPreferences.edit()

        binding.emailGender.setOnClickListener {

            val email = binding.email.text.toString()
            val gender = binding.genderText.text.toString()

            if (email.isEmpty()){
                Toast.makeText(activity, "Please fill the data", Toast.LENGTH_SHORT).show()
            }else if (gender.isEmpty()){
                Toast.makeText(activity, "Please fill the data", Toast.LENGTH_SHORT).show()
            }else {
                // Save the name and dob to SharedPreferences
                editor.putString("email", email)
                editor.putString("gender", gender)
                editor.apply()

                val intent = Intent(requireContext(), HomeNavControl::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}