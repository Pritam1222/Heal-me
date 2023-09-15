package com.example.heal_me.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentTermsConditionsBinding

class TermsConditionsFragment : Fragment() {

    private var _binding: FragmentTermsConditionsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTermsConditionsBinding.inflate(inflater, container, false)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbarTC = binding.toolbarTc
        toolbarTC.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbarTC)

        toolbarTC.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}