package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heal_me.adapters.CanceledAppointmentAdapter
import com.example.heal_me.data.CanceledAppointmentDataItem
import com.example.heal_me.databinding.FragmentCanceledAppointmentBinding


class CanceledAppointmentFragment : Fragment() {
    private lateinit var fragmentCanceledAppointmentBinding : FragmentCanceledAppointmentBinding
    private lateinit var canceledAppointmentAdapter: CanceledAppointmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCanceledAppointmentBinding = FragmentCanceledAppointmentBinding.inflate(layoutInflater)
        canceledAppointmentAdapter = CanceledAppointmentAdapter()

        val canceledAppointmentObject = mutableListOf<CanceledAppointmentDataItem>()
        canceledAppointmentObject.add(CanceledAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        canceledAppointmentObject.add(CanceledAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        canceledAppointmentObject.add(CanceledAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        canceledAppointmentObject.add(CanceledAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        canceledAppointmentObject.add(CanceledAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        canceledAppointmentObject.add(CanceledAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))
        canceledAppointmentObject.add(CanceledAppointmentDataItem("https://as1.ftcdn.net/v2/jpg/02/91/43/02/1000_F_291430206_U1vohsIJWJvF3wb1a3uxfYLPCfXMRbum.jpg","Dr. Bruno Rodrigus", "Heart surgeon","Friday","March 27","08:00","09:00"))

        canceledAppointmentAdapter.submitList(canceledAppointmentObject)
        fragmentCanceledAppointmentBinding.canceledAppointmentRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = canceledAppointmentAdapter
        }

        return fragmentCanceledAppointmentBinding.root
    }

}