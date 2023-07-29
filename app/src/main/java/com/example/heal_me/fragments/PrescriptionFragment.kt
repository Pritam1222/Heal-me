package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentPrescriptionBinding


class PrescriptionFragment : Fragment() {
    lateinit var fragmentPrescriptionBinding: FragmentPrescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentPrescriptionBinding = FragmentPrescriptionBinding.inflate(layoutInflater)



        return fragmentPrescriptionBinding.root
    }

}