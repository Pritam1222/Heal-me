package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.heal_me.R
import com.example.heal_me.adapters.AppointmentViewPagerAdapter
import com.example.heal_me.databinding.FragmentAppointmentBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator


class AppointmentFragment : Fragment() {
    private lateinit var fragmentAppointmentBinding: FragmentAppointmentBinding
    private val tabTitle = arrayListOf("Upcoming","Completed","Canceled")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAppointmentBinding = FragmentAppointmentBinding.inflate(layoutInflater)
        setUpTabLayoutWithViewPager()

        fragmentAppointmentBinding.setting.setOnClickListener {
            it.findNavController().navigate(R.id.action_appointmentFragment_to_settingFragment)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
        }

        return fragmentAppointmentBinding.root

    }

    private fun setUpTabLayoutWithViewPager() {
        fragmentAppointmentBinding.vpAppointmentViewPager2.adapter = AppointmentViewPagerAdapter(this)
        TabLayoutMediator(fragmentAppointmentBinding.tlAppointmentTabLayout, fragmentAppointmentBinding.vpAppointmentViewPager2){tab, position ->
            tab.text = tabTitle[position]
        }.attach()


        for(i in 0..3){
            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_title, null)
                    as TextView
            fragmentAppointmentBinding.tlAppointmentTabLayout.getTabAt(i)?.customView =textView
        }
    }


}