package com.example.heal_me.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heal_me.data.CanceledAppointmentDataItem
import com.example.heal_me.databinding.CanceledAppointmentItemViewBinding


class CanceledAppointmentAdapter : ListAdapter<CanceledAppointmentDataItem, CanceledAppointmentAdapter.CanceledAppointmentInfoViewHolder>(CanceledAppointmentAdapter.DATA_COMPARATOR){

    private companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<CanceledAppointmentDataItem>() {
            override fun areItemsTheSame(
                oldItem: CanceledAppointmentDataItem,
                newItem: CanceledAppointmentDataItem
            ): Boolean = oldItem.canceledAppointmentDoctorImage == newItem.canceledAppointmentDoctorImage

            override fun areContentsTheSame(
                oldItem: CanceledAppointmentDataItem,
                newItem: CanceledAppointmentDataItem
            ): Boolean = oldItem == newItem

        }
    }


    class CanceledAppointmentInfoViewHolder(private val canceledAppointmentItemViewBinding: CanceledAppointmentItemViewBinding): RecyclerView.ViewHolder(canceledAppointmentItemViewBinding.root){
        fun bind(canceledAppointmentDataItem: CanceledAppointmentDataItem){
            Glide.with(canceledAppointmentItemViewBinding.root)
                .load(canceledAppointmentDataItem.canceledAppointmentDoctorImage)
                .into(canceledAppointmentItemViewBinding.ivCanceledAppointmentDoctorImage)
            canceledAppointmentItemViewBinding.tvCanceledAppointmentDoctorName.text = canceledAppointmentDataItem.canceledAppointmentDoctorName
            canceledAppointmentItemViewBinding.tvCanceledAppointmentDoctorSpeciality.text = canceledAppointmentDataItem.canceledAppointmentDoctorSpeciality
            canceledAppointmentItemViewBinding.canceledAppointmentDay.text = canceledAppointmentDataItem.canceledAppointmentDay
            canceledAppointmentItemViewBinding.canceledAppointmentDate.text = canceledAppointmentDataItem.canceledAppointmentDate
            canceledAppointmentItemViewBinding.canceledAppointmentTimeFrom.text = canceledAppointmentDataItem.canceledAppointmentTimeFrom
            canceledAppointmentItemViewBinding.canceledAppointmentTimeTo.text = canceledAppointmentDataItem.canceledAppointmentTimeTo

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
    }

}