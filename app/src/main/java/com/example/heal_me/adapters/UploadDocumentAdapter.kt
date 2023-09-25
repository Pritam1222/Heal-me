package com.example.heal_me.adapters

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heal_me.R
import com.example.heal_me.data.Appointment
import com.example.heal_me.data.Doctors
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.data.UploadDoc
import com.example.heal_me.databinding.DoctorsInfoItemViewBinding
import com.example.heal_me.databinding.UploadFragmentItemViewBinding
import com.example.heal_me.fragments.DoctorsFragmentDirections
import com.example.heal_me.fragments.FavouriteDoctorFragmentDirections
import com.example.heal_me.fragments.HomeFragmentDirections
import com.example.heal_me.fragments.UploadFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UploadDocumentAdapter (private val bottomNavigationView: BottomNavigationView, private val database: DoctorsDatabase) : ListAdapter<UploadDoc, UploadDocumentAdapter.UploadDocInfoViewHolder>(UploadDocumentAdapter.DATA_COMPARATOR) {

    private lateinit var navController : NavController

    private companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<UploadDoc>() {
            override fun areItemsTheSame(
                oldItem: UploadDoc,
                newItem: UploadDoc
            ): Boolean = oldItem.patientName == newItem.patientName

            override fun areContentsTheSame(
                oldItem: UploadDoc,
                newItem: UploadDoc
            ): Boolean = oldItem == newItem

        }
    }

    class UploadDocInfoViewHolder(private val uploadFragmentItemViewBinding: UploadFragmentItemViewBinding,
                                  private val showCustomDialog: (Context, UploadDoc) -> Unit): RecyclerView.ViewHolder(uploadFragmentItemViewBinding.root){
        fun bind(uploadDocInfoDataItem: UploadDoc){
            uploadFragmentItemViewBinding.tvUploadDate.text = uploadDocInfoDataItem.documentDate.toString()
            uploadFragmentItemViewBinding.tvUploadMonth.text = uploadDocInfoDataItem.documentMonth
            uploadFragmentItemViewBinding.tvUploadPatientName.text = uploadDocInfoDataItem.patientName

            uploadFragmentItemViewBinding.clUploadDelete.setOnClickListener {
                // Show the custom dialog when the button is clicked
                showCustomDialog(uploadFragmentItemViewBinding.root.context, uploadDocInfoDataItem)
            }
        }
        val docInfo = uploadFragmentItemViewBinding.clUploadNext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UploadDocumentAdapter.UploadDocInfoViewHolder {
        return UploadDocumentAdapter.UploadDocInfoViewHolder(
            UploadFragmentItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            this::showCustomDialog
        )
    }

    override fun onBindViewHolder(holder: UploadDocumentAdapter.UploadDocInfoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
        val selectedDocumentId = getItem(position)?.documentId

        holder.docInfo.setOnClickListener {
            val navHostFragment = (holder.itemView.context as FragmentActivity).supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController

            val action: NavDirections = UploadFragmentDirections.actionUploadFragmentToDocumentInfoFragment(
                selectedDocumentId!!
            )
            navController.navigate(action)

            // Hide the BottomNavigationView
            bottomNavigationView.visibility = View.GONE

        }
    }

    private fun showCustomDialog(context: Context, item: UploadDoc) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_cancel_upcoming_appointment)
        val dialogTitle = dialog.findViewById<TextView>(R.id.dialog_title)
        val dialogQuestion = dialog.findViewById<TextView>(R.id.dialog_question)
        val dialogDescription = dialog.findViewById<TextView>(R.id.dialog_description)

        dialogTitle.text = "Delete Document"
        dialogQuestion.text = "Do you want to delete\nDocument"
        dialogDescription.text = "If you want to delete a document,\nyou can remove it anytime"

        val dialogOKButton = dialog.findViewById<Button>(R.id.dialogOKButton)
        dialogOKButton.setOnClickListener {
            // Handle the "OK" button click
            deleteItem(item.documentId)
//            Toast.makeText(context, "Item updated", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        val dialogCancelButton = dialog.findViewById<Button>(R.id.dialogCancelButton)
        dialogCancelButton.setOnClickListener {
            // Handle the "Cancel" button click
            dialog.dismiss()
        }

        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteItem(documentId : Long) {

        GlobalScope.launch(Dispatchers.IO) {
            // Delete the item from the Room database within a coroutine
            database.uploadDocDao().deleteDocumentById(documentId)
        }
        // Notify the adapter that the data set has changed
        notifyDataSetChanged()

    }

}