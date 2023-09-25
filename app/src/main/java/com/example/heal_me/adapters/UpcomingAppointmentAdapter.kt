package com.example.heal_me.adapters

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heal_me.AppointmentSharedViewModel
import com.example.heal_me.R
import com.example.heal_me.data.Appointment
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.databinding.UpcomingAppointmentItemViewBinding
import com.example.heal_me.fragments.HomeFragmentDirections


interface OnAppointmentUpdateListener {
    fun onUpdateAppointment(appointmentId : Long, appointment: Appointment)
}

class UpcomingAppointmentAdapter(private val context: Context, private val doctorsDatabase: DoctorsDatabase, private val appointmentUpdateListener: OnAppointmentUpdateListener, private val sharedViewModel: AppointmentSharedViewModel) : ListAdapter<Appointment, UpcomingAppointmentAdapter.UpcomingAppointmentInfoViewHolder>(UpcomingAppointmentAdapter.DATA_COMPARATOR){

    private companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<Appointment>() {
            override fun areItemsTheSame(
                oldItem: Appointment,
                newItem: Appointment
            ): Boolean = oldItem.doctorImage == newItem.doctorImage

            override fun areContentsTheSame(
                oldItem: Appointment,
                newItem: Appointment
            ): Boolean = oldItem == newItem

        }
    }


    class UpcomingAppointmentInfoViewHolder(val upcomingAppointmentItemViewBinding: UpcomingAppointmentItemViewBinding,
                                            private val showCustomDialog: (Context, Appointment) -> Unit): RecyclerView.ViewHolder(upcomingAppointmentItemViewBinding.root){

        fun bind(upcomingAppointmentDataItem: Appointment){
            Glide.with(upcomingAppointmentItemViewBinding.root)
                .load(upcomingAppointmentDataItem.doctorImage)
                .into(upcomingAppointmentItemViewBinding.ivUpcomingAppointmentDoctorImage)
            upcomingAppointmentItemViewBinding.tvUpcomingAppointmentDoctorName.text = upcomingAppointmentDataItem.doctorName
            upcomingAppointmentItemViewBinding.tvUpcomingAppointmentDoctorSpeciality.text = upcomingAppointmentDataItem.doctorSpecialty
            upcomingAppointmentItemViewBinding.upcomingAppointmentDay.text = upcomingAppointmentDataItem.appointmentDay
            upcomingAppointmentItemViewBinding.upcomingAppointmentDate.text = upcomingAppointmentDataItem.appointmentDate
            upcomingAppointmentItemViewBinding.upcomingAppointmentTimeFrom.text = upcomingAppointmentDataItem.appointmentFromTime
            upcomingAppointmentItemViewBinding.upcomingAppointmentTimeTo.text = upcomingAppointmentDataItem.appointmentToTime

            upcomingAppointmentItemViewBinding.upcomingAppointmentCancelButton.setOnClickListener {
                // Show the custom dialog when the button is clicked
                showCustomDialog(upcomingAppointmentItemViewBinding.root.context, upcomingAppointmentDataItem)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpcomingAppointmentInfoViewHolder {
        return UpcomingAppointmentInfoViewHolder(
            UpcomingAppointmentItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            this::showCustomDialog
        )
    }

    override fun onBindViewHolder(holder: UpcomingAppointmentInfoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
        val selectedItemId = getItem(position)?.id
        holder.upcomingAppointmentItemViewBinding.upcomingAppointmentRescheduleButton.setOnClickListener {
            // Update the selected appointment ID in the shared ViewModel
            sharedViewModel.setSelectedAppointmentId(selectedItemId)
        }
    }

    private fun showCustomDialog(context: Context, item: Appointment) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_cancel_upcoming_appointment)

        val dialogOKButton = dialog.findViewById<Button>(R.id.dialogOKButton)
        dialogOKButton.setOnClickListener {
            // Handle the "OK" button click
            appointmentUpdateListener.onUpdateAppointment(item.id, item)
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

}