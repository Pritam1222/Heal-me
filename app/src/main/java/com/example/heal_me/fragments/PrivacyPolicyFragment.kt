package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentPrivacyPolicyBinding

class PrivacyPolicyFragment : Fragment() {

    private lateinit var fragmentPrivacyPolicyBinding: FragmentPrivacyPolicyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentPrivacyPolicyBinding = FragmentPrivacyPolicyBinding.inflate(layoutInflater)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbarPrivacyPolicy = fragmentPrivacyPolicyBinding.toolbarPrivacyPolicy
        toolbarPrivacyPolicy.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbarPrivacyPolicy)

        toolbarPrivacyPolicy.setNavigationOnClickListener {
            findNavController().navigateUp()
        }


        return fragmentPrivacyPolicyBinding.root
    }


}