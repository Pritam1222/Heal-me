package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heal_me.adapters.UpcomingAppointmentAdapter
import com.example.heal_me.data.UpcomingAppointmentDataItem
import com.example.heal_me.databinding.FragmentUpcomingAppointmentBinding


class UpcomingAppointmentFragment : Fragment() {
    private lateinit var fragmentUpcomingAppointmentBinding : FragmentUpcomingAppointmentBinding
    private lateinit var upcomingAppointmentAdapter: UpcomingAppointmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUpcomingAppointmentBinding = FragmentUpcomingAppointmentBinding.inflate(layoutInflater)
        upcomingAppointmentAdapter = UpcomingAppointmentAdapter()

        val upcomingAppointmentObject = mutableListOf<UpcomingAppointmentDataItem>()
        upcomingAppointmentObject.add(UpcomingAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        upcomingAppointmentObject.add(UpcomingAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        upcomingAppointmentObject.add(UpcomingAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        upcomingAppointmentObject.add(UpcomingAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        upcomingAppointmentObject.add(UpcomingAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        upcomingAppointmentObject.add(UpcomingAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        upcomingAppointmentObject.add(UpcomingAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))

        upcomingAppointmentAdapter.submitList(upcomingAppointmentObject)
        fragmentUpcomingAppointmentBinding.upcomingAppointmentRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = upcomingAppointmentAdapter
        }

        return fragmentUpcomingAppointmentBinding.root
    }

}