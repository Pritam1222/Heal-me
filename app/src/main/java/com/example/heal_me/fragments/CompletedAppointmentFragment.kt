package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heal_me.adapters.CompletedAppointmentAdapter
import com.example.heal_me.data.CompletedAppointmentDataItem
import com.example.heal_me.databinding.FragmentCompletedAppointmentBinding


class CompletedAppointmentFragment : Fragment() {
    private lateinit var fragmentCompletedAppointmentBinding :FragmentCompletedAppointmentBinding
    private lateinit var completedAppointmentAdapter: CompletedAppointmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCompletedAppointmentBinding = FragmentCompletedAppointmentBinding.inflate(layoutInflater)
        completedAppointmentAdapter = CompletedAppointmentAdapter()

        val completedAppointmentObject = mutableListOf<CompletedAppointmentDataItem>()
        completedAppointmentObject.add(CompletedAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        completedAppointmentObject.add(CompletedAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        completedAppointmentObject.add(CompletedAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))

        completedAppointmentAdapter.submitList(completedAppointmentObject)
        fragmentCompletedAppointmentBinding.completedAppointmentRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = completedAppointmentAdapter
        }

        return fragmentCompletedAppointmentBinding.root
    }

}