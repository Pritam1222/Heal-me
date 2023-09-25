package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heal_me.R
import com.example.heal_me.adapters.UploadDocumentAdapter
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.databinding.FragmentPrescriptionBinding
import com.example.heal_me.databinding.FragmentReportBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ReportFragment : Fragment() {
    private lateinit var fragmentReportBinding: FragmentReportBinding
    private lateinit var doctorsDatabase: DoctorsDatabase
    private lateinit var uploadDocumentAdapter: UploadDocumentAdapter
    private var bottomNavigationView: BottomNavigationView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentReportBinding = FragmentReportBinding.inflate(layoutInflater)

        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())

        bottomNavigationView = activity?.findViewById(R.id.bottom_navigation)
        uploadDocumentAdapter = UploadDocumentAdapter(bottomNavigationView!!, doctorsDatabase)

        doctorsDatabase.uploadDocDao().getDocumentByType("report").observe(viewLifecycleOwner) { data ->
            uploadDocumentAdapter.submitList(data)
        }

        fragmentReportBinding.rvReportDocument.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = uploadDocumentAdapter
        }

        return fragmentReportBinding.root
    }

}