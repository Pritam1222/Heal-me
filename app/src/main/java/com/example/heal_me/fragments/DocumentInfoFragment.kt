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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.heal_me.R
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.databinding.FragmentDocumentInfoBinding

class DocumentInfoFragment : Fragment() {
    private lateinit var fragmentDocumentInfoBinding: FragmentDocumentInfoBinding
    private lateinit var doctorsDatabase: DoctorsDatabase
    private val args: DocumentInfoFragmentArgs by navArgs()
    private lateinit var documentUri : Uri
    private lateinit var imageUri : Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDocumentInfoBinding = FragmentDocumentInfoBinding.inflate(layoutInflater)
        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())
        val documentId = args.documentId

        doctorsDatabase.uploadDocDao().getDocumentById(documentId).observe(viewLifecycleOwner, Observer { entity ->
            if (entity != null) {

                documentUri = Uri.parse(entity.documentUri)
                imageUri = Uri.parse(entity.imageUri)

                if(entity.imageUri.isNotEmpty()){
                    // Set the image Uri to the ImageView
                    fragmentDocumentInfoBinding.ivDocument.setImageURI(imageUri)
                }else{
                    try {
                        val parcelFileDescriptor: ParcelFileDescriptor? =
                            requireActivity().contentResolver.openFileDescriptor(documentUri, "r")

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
            openDocument()
        }

        return fragmentDocumentInfoBinding.root
    }

    private fun openDocument() {
        Log.d("DocumentUri", documentUri.toString()) // Log the documentUri

        val openIntent = Intent(Intent.ACTION_VIEW)
        openIntent.setDataAndType(documentUri, "application/pdf")
        openIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

        val packageManager = requireActivity().packageManager

        if (openIntent.resolveActivity(packageManager) != null) {
            startActivity(openIntent)
        } else {
            Log.e("OpenDocument", "No app to open this document.")
            Toast.makeText(requireContext(), "No app to open this PDF document.", Toast.LENGTH_SHORT).show()
        }
    }

}