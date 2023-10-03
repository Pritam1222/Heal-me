package com.example.heal_me.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heal_me.AppointmentSharedViewModel
import com.example.heal_me.data.Appointment
import com.example.heal_me.databinding.CanceledAppointmentItemViewBinding


class CanceledAppointmentAdapter(private val sharedViewModel: AppointmentSharedViewModel) : ListAdapter<Appointment, CanceledAppointmentAdapter.CanceledAppointmentInfoViewHolder>(CanceledAppointmentAdapter.DATA_COMPARATOR){

    private companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<Appointment>() {
            override fun areItemsTheSame(
                oldItem: Appointment,
                newItem: Appointment
            ): Boolean = oldItem.doctorName == newItem.doctorName

            override fun areContentsTheSame(
                oldItem: Appointment,
                newItem: Appointment
            ): Boolean = oldItem == newItem

        }
    }


    class CanceledAppointmentInfoViewHolder(val canceledAppointmentItemViewBinding: CanceledAppointmentItemViewBinding): RecyclerView.ViewHolder(canceledAppointmentItemViewBinding.root){
        fun bind(canceledAppointmentDataItem: Appointment){
            Glide.with(canceledAppointmentItemViewBinding.root)
                .load(canceledAppointmentDataItem.doctorImage)
                .into(canceledAppointmentItemViewBinding.ivCanceledAppointmentDoctorImage)
            canceledAppointmentItemViewBinding.tvCanceledAppointmentDoctorName.text = canceledAppointmentDataItem.doctorName
            canceledAppointmentItemViewBinding.tvCanceledAppointmentDoctorSpeciality.text = canceledAppointmentDataItem.doctorSpecialty
            canceledAppointmentItemViewBinding.canceledAppointmentDay.text = canceledAppointmentDataItem.appointmentDay
            canceledAppointmentItemViewBinding.canceledAppointmentDate.text = canceledAppointmentDataItem.appointmentDate
            canceledAppointmentItemViewBinding.canceledAppointmentTimeFrom.text = canceledAppointmentDataItem.appointmentFromTime
            canceledAppointmentItemViewBinding.canceledAppointmentTimeTo.text = canceledAppointmentDataItem.appointmentToTime

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CanceledAppointmentInfoViewHolder {
        return CanceledAppointmentAdapter.CanceledAppointmentInfoViewHolder(
            CanceledAppointmentItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CanceledAppointmentInfoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
        val selectedItemId = getItem(position)?.id
        holder.canceledAppointmentItemViewBinding.canceledAppointmentRescheduleButton.setOnClickListener {
            // Update the selected appointment ID in the shared ViewModel
            sharedViewModel.setSelectedAppointmentId(selectedItemId)
        }
    }

}