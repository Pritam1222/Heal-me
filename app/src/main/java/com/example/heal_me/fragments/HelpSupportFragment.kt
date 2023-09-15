package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentHelpSupportBinding

class HelpSupportFragment : Fragment() {

    private lateinit var fragmentHelpSupportBinding: FragmentHelpSupportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHelpSupportBinding = FragmentHelpSupportBinding.inflate(layoutInflater)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbarHelpSupport = fragmentHelpSupportBinding.toolbarHelpSupport
        toolbarHelpSupport.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbarHelpSupport)

        toolbarHelpSupport.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        return fragmentHelpSupportBinding.root
    }

}