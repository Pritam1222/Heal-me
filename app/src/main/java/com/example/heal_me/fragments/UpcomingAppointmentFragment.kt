package com.example.heal_me.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heal_me.AppointmentSharedViewModel
import com.example.heal_me.adapters.OnAppointmentUpdateListener
import com.example.heal_me.adapters.UpcomingAppointmentAdapter
import com.example.heal_me.data.Appointment
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.databinding.FragmentUpcomingAppointmentBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class UpcomingAppointmentFragment : Fragment(), OnAppointmentUpdateListener {
    private lateinit var fragmentUpcomingAppointmentBinding : FragmentUpcomingAppointmentBinding
    private lateinit var upcomingAppointmentAdapter: UpcomingAppointmentAdapter
    private lateinit var doctorsDatabase: DoctorsDatabase
    private val sharedViewModel: AppointmentSharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUpcomingAppointmentBinding = FragmentUpcomingAppointmentBinding.inflate(layoutInflater)
        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())

        upcomingAppointmentAdapter = UpcomingAppointmentAdapter(requireContext(), doctorsDatabase, this, sharedViewModel)

        doctorsDatabase.appointmentDao().getAppointmentByType("Upcoming").observe(viewLifecycleOwner) { data ->
            upcomingAppointmentAdapter.submitList(data)
        }

        fragmentUpcomingAppointmentBinding.upcomingAppointmentRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = upcomingAppointmentAdapter
        }

        return fragmentUpcomingAppointmentBinding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("NotifyDataSetChanged")
    override fun onUpdateAppointment(appointmentId: Long, appointment: Appointment) {
        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())

        // Update the appointment type to "Canceled"
        appointment.appointmentType = "Canceled"

        GlobalScope.launch {
            try {
                doctorsDatabase.appointmentDao().updateAppointment(appointment)
//                println(appointment.doctorId)
                upcomingAppointmentAdapter.notifyDataSetChanged()
            } catch (e: Exception) {
                e.printStackTrace()
                // Log or handle the exception as needed
            }
        }
        upcomingAppointmentAdapter.notifyDataSetChanged()
    }

}