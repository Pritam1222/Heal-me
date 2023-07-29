package com.example.heal_me.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentUploadPreviewBinding
import java.text.DateFormat
import java.util.Calendar

class UploadPreviewFragment : Fragment() {
    private lateinit var fragmentUploadPreviewBinding: FragmentUploadPreviewBinding
    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUploadPreviewBinding = FragmentUploadPreviewBinding.inflate(layoutInflater)

        val navHostFragment = (context as FragmentActivity).supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val calendar = Calendar.getInstance().time
        val dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar)

        fragmentUploadPreviewBinding.uploadReviewDate.text = dateFormat

        fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setOnClickListener {

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setTextColor(Color.parseColor("#FFFFFF"))
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setTextColor(Color.parseColor("#000000"))
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setTextColor(Color.parseColor("#000000"))

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setBackgroundResource(R.drawable.upload_category_selected_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setBackgroundResource(R.drawable.upload_category_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setBackgroundResource(R.drawable.upload_category_tab_background)
        }

        fragmentUploadPreviewBinding.ivUploadPreviewReport.setOnClickListener {

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setTextColor(Color.parseColor("#FFFFFF"))
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setTextColor(Color.parseColor("#000000"))
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setTextColor(Color.parseColor("#000000"))

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setBackgroundResource(R.drawable.upload_category_selected_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setBackgroundResource(R.drawable.upload_category_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setBackgroundResource(R.drawable.upload_category_tab_background)
        }

        fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setOnClickListener {

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setTextColor(Color.parseColor("#000000"))
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setTextColor(Color.parseColor("#FFFFFF"))
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setTextColor(Color.parseColor("#000000"))

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setBackgroundResource(R.drawable.upload_category_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setBackgroundResource(R.drawable.upload_category_selected_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setBackgroundResource(R.drawable.upload_category_tab_background)
        }

        fragmentUploadPreviewBinding.ivUploadPreviewPrescription.setOnClickListener {

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setTextColor(Color.parseColor("#000000"))
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setTextColor(Color.parseColor("#FFFFFF"))
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setTextColor(Color.parseColor("#000000"))

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setBackgroundResource(R.drawable.upload_category_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setBackgroundResource(R.drawable.upload_category_selected_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setBackgroundResource(R.drawable.upload_category_tab_background)
        }

        fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setOnClickListener {

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setTextColor(Color.parseColor("#000000"))
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setTextColor(Color.parseColor("#000000"))
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setTextColor(Color.parseColor("#FFFFFF"))

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setBackgroundResource(R.drawable.upload_category_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setBackgroundResource(R.drawable.upload_category_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setBackgroundResource(R.drawable.upload_category_selected_tab_background)
        }

        fragmentUploadPreviewBinding.ivUploadPreviewInvoice.setOnClickListener {

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setTextColor(Color.parseColor("#000000"))
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setTextColor(Color.parseColor("#000000"))
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setTextColor(Color.parseColor("#FFFFFF"))

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setBackgroundResource(R.drawable.upload_category_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setBackgroundResource(R.drawable.upload_category_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setBackgroundResource(R.drawable.upload_category_selected_tab_background)
        }

        fragmentUploadPreviewBinding.clUploadReportsButton.setOnClickListener {
            val action: NavDirections = UploadPreviewFragmentDirections.actionUploadPreviewFragmentToUploadFragment()
            navController.navigate(action)

        }

        return fragmentUploadPreviewBinding.root
    }

}