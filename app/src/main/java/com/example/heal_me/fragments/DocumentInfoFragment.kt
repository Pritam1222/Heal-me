package com.example.heal_me.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.heal_me.R
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.databinding.FragmentDocumentInfoBinding

class DocumentInfoFragment : Fragment() {
    private lateinit var fragmentDocumentInfoBinding: FragmentDocumentInfoBinding
    private lateinit var doctorsDatabase: DoctorsDatabase
    private val args: DocumentInfoFragmentArgs by navArgs()
    private lateinit var parsedDocumentUri : Uri
    private lateinit var parsedImageUri : Uri
    private lateinit var documentUri : String
    private lateinit var imageUri : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDocumentInfoBinding = FragmentDocumentInfoBinding.inflate(layoutInflater)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbar = fragmentDocumentInfoBinding.toolbarDocInfo
        toolbar.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())
        val documentId = args.documentId

        doctorsDatabase.uploadDocDao().getDocumentById(documentId).observe(viewLifecycleOwner, Observer { entity ->
            if (entity != null) {

                documentUri = entity.documentUri
                imageUri = entity.imageUri

                parsedDocumentUri = Uri.parse(documentUri)
                parsedImageUri = Uri.parse(imageUri)

                if(entity.imageUri.isNotEmpty()){
                    // Set the image Uri to the ImageView
                    fragmentDocumentInfoBinding.ivDocument.setImageURI(parsedImageUri)
                }else{
                    try {
                        val parcelFileDescriptor: ParcelFileDescriptor? =
                            requireActivity().contentResolver.openFileDescriptor(parsedDocumentUri, "r")

                        parcelFileDescriptor?.let {
                            val pdfRenderer = PdfRenderer(it)
                            val page: PdfRenderer.Page = pdfRenderer.openPage(0)

                            val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
                            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

                            fragmentDocumentInfoBinding.ivDocument.setImageBitmap(bitmap)

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

                fragmentDocumentInfoBinding.tvDocInfoName.text = entity.patientName
                fragmentDocumentInfoBinding.tvDocInfoDate.text = entity.documentCompleteDate
                fragmentDocumentInfoBinding.tvDocInfoTime.text = entity.documentTime

            } else {
                // Entity with the given ID not found
            }
        })

        fragmentDocumentInfoBinding.ivDocument.setOnClickListener{
            if(imageUri.isNotEmpty()){
                openImage()
            }else{
                openDocument(parsedDocumentUri)
            }
        }

        return fragmentDocumentInfoBinding.root
    }

    private fun openImage(){
        // Create an intent to view the image using an image viewer
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.setDataAndType(parsedImageUri, "image/*")

        // Verify that an activity can handle the intent before starting it
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        }else{
            Toast.makeText(requireContext(), "No app to open this Image.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openDocument(parsedDocumentUri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(parsedDocumentUri, "application/pdf") // Change the MIME type as per your document type

        // Check if there's an app that can handle the document type
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "No app to open this PDF Document.", Toast.LENGTH_SHORT).show()
        }
    }
}