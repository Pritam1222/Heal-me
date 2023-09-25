package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heal_me.adapters.CanceledAppointmentAdapter
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.databinding.FragmentCanceledAppointmentBinding


class CanceledAppointmentFragment : Fragment() {
    private lateinit var fragmentCanceledAppointmentBinding : FragmentCanceledAppointmentBinding
    private lateinit var canceledAppointmentAdapter: CanceledAppointmentAdapter
    private lateinit var doctorsDatabase: DoctorsDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCanceledAppointmentBinding = FragmentCanceledAppointmentBinding.inflate(layoutInflater)
        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())
        canceledAppointmentAdapter = CanceledAppointmentAdapter()

        doctorsDatabase.appointmentDao().getAppointmentByType("Canceled").observe(viewLifecycleOwner) { data ->
            canceledAppointmentAdapter.submitList(data)
        }

        fragmentCanceledAppointmentBinding.canceledAppointmentRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = canceledAppointmentAdapter
        }

        return fragmentCanceledAppointmentBinding.root
    }

}