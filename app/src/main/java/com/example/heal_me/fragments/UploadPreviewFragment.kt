package com.example.heal_me.fragments

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.heal_me.R
import com.example.heal_me.data.Appointment
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.data.UploadDoc
import com.example.heal_me.databinding.FragmentUploadPreviewBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class UploadPreviewFragment : Fragment() {

    private lateinit var fragmentUploadPreviewBinding: FragmentUploadPreviewBinding
    private lateinit var navController : NavController
    private lateinit var doctorsDatabase: DoctorsDatabase

    private val args: UploadPreviewFragmentArgs by navArgs()

    private var reportStatus = 0
    private var prescriptionStatus = 0
    private var invoiceStatus = 0
    private var documentType = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUploadPreviewBinding = FragmentUploadPreviewBinding.inflate(layoutInflater)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbar = fragmentUploadPreviewBinding.toolbarUploadPreview
        toolbar.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())

        val argDocumentUri = args.documentUris
        val argImageUri = args.imageUris

        val documentUri = Uri.parse(argDocumentUri)
        val imageUri = Uri.parse(argImageUri)

        if(argImageUri.isNotEmpty()){
            // Set the image Uri to the ImageView
            fragmentUploadPreviewBinding.docView.setImageURI(imageUri)
        }else{
            try {
                val parcelFileDescriptor: ParcelFileDescriptor? =
                    requireActivity().contentResolver.openFileDescriptor(documentUri, "r")

                parcelFileDescriptor?.let {
                    val pdfRenderer = PdfRenderer(it)
                    val page: PdfRenderer.Page = pdfRenderer.openPage(0)

                    val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

                    fragmentUploadPreviewBinding.docView.setImageBitmap(bitmap)

                    // Don't forget to close the PdfRenderer and ParcelFileDescriptor when done
                    page.close()
                    pdfRenderer.close()
                    parcelFileDescriptor.close()
                }
            } catch (e: Exception) {
                // Handle exceptions here
                e.printStackTrace()
            }
        }

        val navHostFragment = (context as FragmentActivity).supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val calendar = Calendar.getInstance().time
        val dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar)

        fragmentUploadPreviewBinding.uploadReviewDate.text = dateFormat

        fragmentUploadPreviewBinding.llUploadPreviewReport.setOnClickListener {

            reportStatus = 1
            prescriptionStatus = 0
            invoiceStatus = 0
            documentType = "report"

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setTextColor(Color.parseColor("#FFFFFF"))
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setTextColor(Color.parseColor("#000000"))
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setTextColor(Color.parseColor("#000000"))

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setBackgroundResource(R.drawable.upload_category_selected_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setBackgroundResource(R.drawable.upload_category_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setBackgroundResource(R.drawable.upload_category_tab_background)
        }

        fragmentUploadPreviewBinding.llUploadPreviewPrescription.setOnClickListener {

            reportStatus = 0
            prescriptionStatus = 1
            invoiceStatus = 0
            documentType = "prescription"


            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setTextColor(Color.parseColor("#000000"))
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setTextColor(Color.parseColor("#FFFFFF"))
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setTextColor(Color.parseColor("#000000"))

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setBackgroundResource(R.drawable.upload_category_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setBackgroundResource(R.drawable.upload_category_selected_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setBackgroundResource(R.drawable.upload_category_tab_background)
        }

        fragmentUploadPreviewBinding.llUploadPreviewInvoice.setOnClickListener {

            reportStatus = 0
            prescriptionStatus = 0
            invoiceStatus = 1
            documentType = "invoice"

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setTextColor(Color.parseColor("#000000"))
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setTextColor(Color.parseColor("#000000"))
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setTextColor(Color.parseColor("#FFFFFF"))

            fragmentUploadPreviewBinding.tvUploadPreviewReportTab.setBackgroundResource(R.drawable.upload_category_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewPrescriptionTab.setBackgroundResource(R.drawable.upload_category_tab_background)
            fragmentUploadPreviewBinding.tvUploadPreviewInvoiceTab.setBackgroundResource(R.drawable.upload_category_selected_tab_background)
        }

        val currentDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val currentMonth = SimpleDateFormat("MMM", Locale.getDefault()).format(Calendar.getInstance().time)
        val currentCompleteDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Calendar.getInstance().time)
        val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Calendar.getInstance().time)


        fragmentUploadPreviewBinding.clUploadReportsButton.setOnClickListener {

            if(fragmentUploadPreviewBinding.uploadPreviewPatientName.text.toString().isEmpty()){
                Toast.makeText(context, "Please enter Patient name", Toast.LENGTH_SHORT).show()
            }else if(reportStatus == 0 && prescriptionStatus == 0 && invoiceStatus == 0){
                Toast.makeText(context, "Please select Record type", Toast.LENGTH_SHORT).show()
            }else{

                GlobalScope.launch {
                    try {
                        doctorsDatabase.uploadDocDao().insertDocument(UploadDoc(0, fragmentUploadPreviewBinding.uploadPreviewPatientName.text.toString(), currentDate, currentMonth, currentCompleteDate, currentTime, argDocumentUri, argImageUri, documentType))

                    } catch (e: Exception) {
                        e.printStackTrace()
                        // Log or handle the exception as needed
                    }
                }
                findNavController().navigateUp()
            }

        }

        return fragmentUploadPreviewBinding.root
    }

}