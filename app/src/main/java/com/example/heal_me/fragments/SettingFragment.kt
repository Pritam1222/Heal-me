package com.example.heal_me.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentSettingBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

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

        binding.logout.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment_to_logoutDialogFragment)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
                View.GONE
        }

        binding.settingsMyRewards.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment_to_selectedRewardFragment)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
                View.GONE
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}