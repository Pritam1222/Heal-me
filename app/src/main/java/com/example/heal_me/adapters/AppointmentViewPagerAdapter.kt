package com.example.heal_me.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.heal_me.fragments.CanceledAppointmentFragment
import com.example.heal_me.fragments.CompletedAppointmentFragment
import com.example.heal_me.fragments.UpcomingAppointmentFragment


class AppointmentViewPagerAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0-> UpcomingAppointmentFragment()
            1-> CompletedAppointmentFragment()
            else-> CanceledAppointmentFragment()
        }
    }
}