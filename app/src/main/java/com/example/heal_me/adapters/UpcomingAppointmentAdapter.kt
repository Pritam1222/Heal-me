package com.example.heal_me.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heal_me.data.UpcomingAppointmentDataItem
import com.example.heal_me.databinding.UpcomingAppointmentItemViewBinding

class UpcomingAppointmentAdapter : ListAdapter<UpcomingAppointmentDataItem, UpcomingAppointmentAdapter.UpcomingAppointmentInfoViewHolder>(UpcomingAppointmentAdapter.DATA_COMPARATOR){

    private companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<UpcomingAppointmentDataItem>() {
            override fun areItemsTheSame(
                oldItem: UpcomingAppointmentDataItem,
                newItem: UpcomingAppointmentDataItem
            ): Boolean = oldItem.upcomingAppointmentDoctorImage == newItem.upcomingAppointmentDoctorImage

            override fun areContentsTheSame(
                oldItem: UpcomingAppointmentDataItem,
                newItem: UpcomingAppointmentDataItem
            ): Boolean = oldItem == newItem

        }
    }


    class UpcomingAppointmentInfoViewHolder(private val upcomingAppointmentItemViewBinding: UpcomingAppointmentItemViewBinding): RecyclerView.ViewHolder(upcomingAppointmentItemViewBinding.root){
        fun bind(upcomingAppointmentDataItem: UpcomingAppointmentDataItem){
            Glide.with(upcomingAppointmentItemViewBinding.root)
                .load(upcomingAppointmentDataItem.upcomingAppointmentDoctorImage)
                .into(upcomingAppointmentItemViewBinding.ivUpcomingAppointmentDoctorImage)
            upcomingAppointmentItemViewBinding.tvUpcomingAppointmentDoctorName.text = upcomingAppointmentDataItem.upcomingAppointmentDoctorName
            upcomingAppointmentItemViewBinding.tvUpcomingAppointmentDoctorSpeciality.text = upcomingAppointmentDataItem.upcomingAppointmentDoctorSpeciality
            upcomingAppointmentItemViewBinding.upcomingAppointmentDay.text = upcomingAppointmentDataItem.upcomingAppointmentDay
            upcomingAppointmentItemViewBinding.upcomingAppointmentDate.text = upcomingAppointmentDataItem.upcomingAppointmentDate
            upcomingAppointmentItemViewBinding.upcomingAppointmentTimeFrom.text = upcomingAppointmentDataItem.upcomingAppointmentTimeFrom
            upcomingAppointmentItemViewBinding.upcomingAppointmentTimeTo.text = upcomingAppointmentDataItem.upcomingAppointmentTimeTo

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpcomingAppointmentInfoViewHolder {
        return UpcomingAppointmentAdapter.UpcomingAppointmentInfoViewHolder(
            UpcomingAppointmentItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UpcomingAppointmentInfoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}