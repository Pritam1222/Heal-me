package com.example.heal_me.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heal_me.R
import com.example.heal_me.adapters.DoctorsInfoAdapter
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.databinding.FragmentDoctorsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DoctorsFragment : Fragment() {

    private lateinit var doctorsInfoAdapter: DoctorsInfoAdapter
    private lateinit var doctorsDatabase: DoctorsDatabase
    private var _binding: FragmentDoctorsBinding? = null
    private var bottomNavigationView: BottomNavigationView? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDoctorsBinding.inflate(inflater, container, false)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbar = binding.toolbarAllDoctors
        toolbar.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())

        bottomNavigationView = activity?.findViewById(R.id.bottom_navigation)

        doctorsInfoAdapter = DoctorsInfoAdapter(bottomNavigationView!!)

        doctorsDatabase.doctorsDao().getDoctor().observe(viewLifecycleOwner) { data ->
            doctorsInfoAdapter.submitList(data)
        }

        binding.rvDoctorsFragmentDoctorsInfo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = doctorsInfoAdapter
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}