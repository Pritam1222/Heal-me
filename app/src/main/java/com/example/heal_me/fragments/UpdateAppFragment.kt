package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.fragment.findNavController
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentUpdateAppBinding


class UpdateAppFragment : Fragment() {

    private lateinit var fragmentUpdateAppBinding: FragmentUpdateAppBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUpdateAppBinding = FragmentUpdateAppBinding.inflate(inflater, container, false)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbarUpdate = fragmentUpdateAppBinding.toolbarUpdateApp
        toolbarUpdate.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbarUpdate)

        toolbarUpdate.setNavigationOnClickListener {
            findNavController().navigateUp()
        }


        return fragmentUpdateAppBinding.root
    }


}