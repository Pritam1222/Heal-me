package com.example.heal_me.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heal_me.R
import com.example.heal_me.data.CompletedAppointmentDataItem
import com.example.heal_me.data.UpcomingAppointmentDataItem
import com.example.heal_me.databinding.CompletedAppointmentItemViewBinding
import com.example.heal_me.databinding.UpcomingAppointmentItemViewBinding

class CompletedAppointmentAdapter : ListAdapter<CompletedAppointmentDataItem, CompletedAppointmentAdapter.CompletedAppointmentViewHolder>(CompletedAppointmentAdapter.DATA_COMPARATOR){

    private companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<CompletedAppointmentDataItem>() {
            override fun areItemsTheSame(
                oldItem: CompletedAppointmentDataItem,
                newItem: CompletedAppointmentDataItem
            ): Boolean = oldItem.completedAppointmentDoctorImage == newItem.completedAppointmentDoctorImage

            override fun areContentsTheSame(
                oldItem: CompletedAppointmentDataItem,
                newItem: CompletedAppointmentDataItem
            ): Boolean = oldItem == newItem

        }
    }

    class CompletedAppointmentViewHolder(private val completedAppointmentItemViewBinding: CompletedAppointmentItemViewBinding): RecyclerView.ViewHolder(completedAppointmentItemViewBinding.root){
        fun bind(completedAppointmentDataItem: CompletedAppointmentDataItem){
            Glide.with(completedAppointmentItemViewBinding.root)
                .load(completedAppointmentDataItem.completedAppointmentDoctorImage)
                .into(completedAppointmentItemViewBinding.ivCompletedAppointmentDoctorImage)
            completedAppointmentItemViewBinding.tvCompletedAppointmentDoctorName.text = completedAppointmentDataItem.completedAppointmentDoctorName
            completedAppointmentItemViewBinding.tvCompletedAppointmentDoctorSpeciality.text = completedAppointmentDataItem.completedAppointmentDoctorSpeciality
            completedAppointmentItemViewBinding.completedAppointmentDay.text = completedAppointmentDataItem.completedAppointmentDay
            completedAppointmentItemViewBinding.completedAppointmentDate.text = completedAppointmentDataItem.completedAppointmentDate
            completedAppointmentItemViewBinding.completedAppointmentTimeFrom.text = completedAppointmentDataItem.completedAppointmentTimeFrom
            completedAppointmentItemViewBinding.completedAppointmentTimeTo.text = completedAppointmentDataItem.completedAppointmentTimeTo

            completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar1.setOnClickListener {
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar1.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar2.setImageResource(
                    R.drawable.ic_rating_unselected_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar3.setImageResource(
                    R.drawable.ic_rating_unselected_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar4.setImageResource(
                    R.drawable.ic_rating_unselected_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar5.setImageResource(
                    R.drawable.ic_rating_unselected_star)

            }

            completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar2.setOnClickListener {
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar1.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar2.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar3.setImageResource(
                    R.drawable.ic_rating_unselected_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar4.setImageResource(
                    R.drawable.ic_rating_unselected_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar5.setImageResource(
                    R.drawable.ic_rating_unselected_star)
            }

            completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar3.setOnClickListener {
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar1.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar2.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar3.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar4.setImageResource(
                    R.drawable.ic_rating_unselected_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar5.setImageResource(
                    R.drawable.ic_rating_unselected_star)
            }

            completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar4.setOnClickListener {
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar1.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar2.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar3.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar4.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar5.setImageResource(
                    R.drawable.ic_rating_unselected_star)
            }

            completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar5.setOnClickListener {
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar1.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar2.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar3.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar4.setImageResource(
                    R.drawable.ic_rating_star)
                completedAppointmentItemViewBinding.ivCompletedAppointmentRatingStar5.setImageResource(
                    R.drawable.ic_rating_star)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompletedAppointmentViewHolder {
        return CompletedAppointmentAdapter.CompletedAppointmentViewHolder(
            CompletedAppointmentItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CompletedAppointmentViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}