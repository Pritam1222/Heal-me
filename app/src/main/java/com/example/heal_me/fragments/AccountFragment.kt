package com.example.heal_me.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

    private lateinit var fragmentAccountBinding: FragmentAccountBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAccountBinding = FragmentAccountBinding.inflate(layoutInflater)

        sharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val mobNum = sharedPreferences.getString("mob_num", "")
        val fullName = sharedPreferences.getString("name", "")
        val dateOfBirth = sharedPreferences.getString("dob", "")
        val email = sharedPreferences.getString("email", "")
        val gender = sharedPreferences.getString("gender", "")

        fragmentAccountBinding.mobNum.text = mobNum.toString()
        fragmentAccountBinding.accountName.text = fullName.toString()
        fragmentAccountBinding.accountDob.text = dateOfBirth.toString()
        fragmentAccountBinding.accountEmail.text = email.toString()
        fragmentAccountBinding.accountGender.text = gender.toString()


        return fragmentAccountBinding.root
    }

}