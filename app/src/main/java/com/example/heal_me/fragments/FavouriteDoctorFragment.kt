package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heal_me.R
import com.example.heal_me.adapters.DoctorsInfoAdapter
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.databinding.FragmentFavouriteDoctorBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavouriteDoctorFragment : Fragment() {
    private lateinit var fragmentFavouriteDoctorBinding: FragmentFavouriteDoctorBinding
    private lateinit var doctorsInfoAdapter: DoctorsInfoAdapter
    private lateinit var doctorsDatabase: DoctorsDatabase
    private var bottomNavigationView: BottomNavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentFavouriteDoctorBinding = FragmentFavouriteDoctorBinding.inflate(layoutInflater)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbar = fragmentFavouriteDoctorBinding.toolbarFavDoc
        toolbar.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())

        bottomNavigationView = activity?.findViewById(R.id.bottom_navigation)
        doctorsInfoAdapter = DoctorsInfoAdapter(bottomNavigationView!!)

        doctorsDatabase.doctorsDao().getDoctorByFavourite(true).observe(viewLifecycleOwner) { data ->
            doctorsInfoAdapter.submitList(data)
        }

        fragmentFavouriteDoctorBinding.rvFavDoctorsInfo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = doctorsInfoAdapter
        }

        bottomNavigationView!!.visibility = View.GONE

        return fragmentFavouriteDoctorBinding.root
    }

}