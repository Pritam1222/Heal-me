package com.example.heal_me.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.heal_me.databinding.FragmentTermsConditionsBinding

class TermsConditionsFragment : Fragment() {

    private var _binding: FragmentTermsConditionsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTermsConditionsBinding.inflate(inflater, container, false)
//        binding.backArrow.setOnClickListener {
//            findNavController().popBackStack()
//        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}