package com.example.heal_me.fragments

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.provider.OpenableColumns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentUploadBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class UploadFragment : Fragment() {
    private lateinit var fragmentUploadBinding: FragmentUploadBinding
    private lateinit var navController : NavController

    private var imageUri: Uri? = null
    private val CAMERA_PERMISSION_CODE = 1
    private val PICK_IMAGE_REQUEST = 2
    private val SELECT_DOCUMENT_REQUEST_CODE = 3


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         fragmentUploadBinding = FragmentUploadBinding.inflate(layoutInflater)

        val navHostFragment = (context as FragmentActivity).supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


         // Set the default selection to cvPrescription
         fragmentUploadBinding.tvPrescription.setTextColor(Color.parseColor("#FFFFFF"))
         fragmentUploadBinding.ivPrescription.setColorFilter(Color.parseColor("#FFFFFF"))
         fragmentUploadBinding.cvPrescription.setCardBackgroundColor(Color.parseColor("#089BAB"))

         val prescriptionFragment = PrescriptionFragment()
         val transaction = requireActivity().supportFragmentManager.beginTransaction()
         transaction.replace(R.id.upload_fragment_container, prescriptionFragment)
         transaction.addToBackStack(null)
         transaction.commit()

         // Set the click listeners on the card views
         fragmentUploadBinding.cvPrescription.setOnClickListener {
             // Change the color of the selected card view's TextView and ImageView
             fragmentUploadBinding.tvPrescription.setTextColor(Color.parseColor("#FFFFFF"))
             fragmentUploadBinding.ivPrescription.setColorFilter(Color.parseColor("#FFFFFF"))
             fragmentUploadBinding.cvPrescription.setCardBackgroundColor(Color.parseColor("#089BAB"))

             fragmentUploadBinding.tvReport.setTextColor(Color.parseColor("#000000"))
             fragmentUploadBinding.ivReport.setColorFilter(Color.parseColor("#089BAB"))
             fragmentUploadBinding.cvReport.setCardBackgroundColor(Color.parseColor("#FFFFFF"))

             fragmentUploadBinding.tvInvoice.setTextColor(Color.parseColor("#000000"))
             fragmentUploadBinding.ivInvoice.setColorFilter(Color.parseColor("#089BAB"))
             fragmentUploadBinding.cvInvoice.setCardBackgroundColor(Color.parseColor("#FFFFFF"))

             // Open the respective fragment
//             val prescriptionFragment = PrescriptionFragment()
//             parentFragmentManager.beginTransaction().replace(R.id.fragment_container, prescriptionFragment).commit()

             val prescriptionFragment = PrescriptionFragment()
             val transaction = requireActivity().supportFragmentManager.beginTransaction()
             transaction.replace(R.id.upload_fragment_container, prescriptionFragment)
             transaction.addToBackStack(null)
             transaction.commit()

         }

         fragmentUploadBinding.cvReport.setOnClickListener {
             // Change the color of the selected card view's TextView and ImageView
             fragmentUploadBinding.tvPrescription.setTextColor(Color.parseColor("#000000"))
             fragmentUploadBinding.ivPrescription.setColorFilter(Color.parseColor("#089BAB"))
             fragmentUploadBinding.cvPrescription.setCardBackgroundColor(Color.parseColor("#FFFFFF"))

             fragmentUploadBinding.tvReport.setTextColor(Color.parseColor("#FFFFFF"))
             fragmentUploadBinding.ivReport.setColorFilter(Color.parseColor("#FFFFFF"))
             fragmentUploadBinding.cvReport.setCardBackgroundColor(Color.parseColor("#089BAB"))

             fragmentUploadBinding.tvInvoice.setTextColor(Color.parseColor("#000000"))
             fragmentUploadBinding.ivInvoice.setColorFilter(Color.parseColor("#089BAB"))
             fragmentUploadBinding.cvInvoice.setCardBackgroundColor(Color.parseColor("#FFFFFF"))

             // Open the respective fragment
//             val reportFragment = ReportFragment()
//             parentFragmentManager.beginTransaction().replace(R.id.fragment_container, reportFragment).commit()

             val reportFragment = ReportFragment()
             val transaction = requireActivity().supportFragmentManager.beginTransaction()
             transaction.replace(R.id.upload_fragment_container, reportFragment)
             transaction.addToBackStack(null)
             transaction.commit()

         }

         fragmentUploadBinding.cvInvoice.setOnClickListener {
             // Change the color of the selected card view's TextView and ImageView
             fragmentUploadBinding.tvPrescription.setTextColor(Color.parseColor("#000000"))
             fragmentUploadBinding.ivPrescription.setColorFilter(Color.parseColor("#089BAB"))
             fragmentUploadBinding.cvPrescription.setCardBackgroundColor(Color.parseColor("#FFFFFF"))

             fragmentUploadBinding.tvReport.setTextColor(Color.parseColor("#000000"))
             fragmentUploadBinding.ivReport.setColorFilter(Color.parseColor("#089BAB"))
             fragmentUploadBinding.cvReport.setCardBackgroundColor(Color.parseColor("#FFFFFF"))

             fragmentUploadBinding.tvInvoice.setTextColor(Color.parseColor("#FFFFFF"))
             fragmentUploadBinding.ivInvoice.setColorFilter(Color.parseColor("#FFFFFF"))
             fragmentUploadBinding.cvInvoice.setCardBackgroundColor(Color.parseColor("#089BAB"))

             val invoiceFragment = InvoiceFragment()
             val transaction = requireActivity().supportFragmentManager.beginTransaction()
             transaction.replace(R.id.upload_fragment_container, invoiceFragment)
             transaction.addToBackStack(null)
             transaction.commit()

         }

         fragmentUploadBinding.clUploadButton.setOnClickListener {

//             openDocument()

             val options = arrayOf("Select Photo","Select Document")
             val builder = AlertDialog.Builder(requireContext())
             builder.setTitle("Choose an option")
             builder.setItems(options) { dialog, which ->
                 when (which) {
                     0 -> openGallery()
                     1 -> openDocument()
                 }
             }
             builder.show()
         }


         return fragmentUploadBinding.root
    }

//    private fun openCamera() {
//        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
//            == PackageManager.PERMISSION_GRANTED) {
//            // Permission is already granted, so launch the camera app
//            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(intent, 0)
//        } else {
//            // Permission is not granted, request for the permission
//            ActivityCompat.requestPermissions(
//                requireActivity(), arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
//        }
//    }

    private fun openGallery() {
        // create an intent to pick an image
        //select image from gallery
        val intent = Intent(Intent.ACTION_PICK)

        //select image from file manager
        //val intent = Intent(Intent.ACTION_GET_CONTENT)

        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun openDocument() {
        // create an intent to pick an document
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "application/pdf"
        startActivityForResult(intent, SELECT_DOCUMENT_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var documentUris : String = ""
        var imageUris : String = ""

        if (requestCode == SELECT_DOCUMENT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { documentUri ->
                val documentName = getDocumentName(documentUri)
                Toast.makeText(requireContext(), "Selected document: $documentName", Toast.LENGTH_SHORT).show()

                // display the document in the ImageView
//                displayDocument(documentUri)

                documentUris = documentUri.toString()
                val action =
                    UploadFragmentDirections.actionUploadFragmentToUploadPreviewFragment(imageUris,documentUris)
                navController.navigate(action)

                activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE

            }
        }else if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            // get the image URI from the data intent
            val imageUri: Uri = data.data!!
            val imageName = getImageName(imageUri)
            Toast.makeText(requireContext(), "Selected image: $imageName", Toast.LENGTH_SHORT).show()

            // display the image in the ImageView
//            fragmentUploadBinding.changeImage.setImageURI(imageUri)

            imageUris = imageUri.toString()
            val action: NavDirections = UploadFragmentDirections.actionUploadFragmentToUploadPreviewFragment(imageUris,documentUris)
            navController.navigate(action)

            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE

        }
//        else if (requestCode == 0 && resultCode == AppCompatActivity.RESULT_OK) {
//            // Image is captured successfully, do something with the captured image
//            val imageBitmap = data?.extras?.get("data") as Bitmap
////            fragmentUploadBinding.changeImage.setImageBitmap(imageBitmap)
//
////            val action: NavDirections = UploadFragmentDirections.actionUploadFragmentToUploadPreviewFragment()
////            navController.navigate(action)
//
//            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
//
//        }
    }

    private fun getImageName(imageUri: Uri): String? {
        val cursor = requireContext().contentResolver.query(imageUri, null, null, null, null)
        val nameIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor?.moveToFirst()
        val name = cursor?.getString(nameIndex ?: 0)
        cursor?.close()
        return name
    }

    private fun getDocumentName(documentUri: Uri): String? {
        val cursor = requireContext().contentResolver.query(documentUri, null, null, null, null)
        val nameIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor?.moveToFirst()
        val name = cursor?.getString(nameIndex ?: 0)
        cursor?.close()
        return name
    }

}

