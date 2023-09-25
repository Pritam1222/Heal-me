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
import com.example.heal_me.databinding.FragmentInvoiceBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class InvoiceFragment : Fragment() {
    private lateinit var fragmentInvoiceBinding: FragmentInvoiceBinding
    private lateinit var doctorsDatabase: DoctorsDatabase
    private lateinit var uploadDocumentAdapter: UploadDocumentAdapter
    private var bottomNavigationView: BottomNavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentInvoiceBinding = FragmentInvoiceBinding.inflate(layoutInflater)

        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())

        bottomNavigationView = activity?.findViewById(R.id.bottom_navigation)
        uploadDocumentAdapter = UploadDocumentAdapter(bottomNavigationView!!, doctorsDatabase)

        doctorsDatabase.uploadDocDao().getDocumentByType("invoice").observe(viewLifecycleOwner) { data ->
            uploadDocumentAdapter.submitList(data)
        }

        fragmentInvoiceBinding.rvInvoiceDocument.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = uploadDocumentAdapter
        }

        return fragmentInvoiceBinding.root
    }

}