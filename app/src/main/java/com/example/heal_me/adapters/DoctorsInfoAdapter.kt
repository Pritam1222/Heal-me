package com.example.heal_me.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heal_me.data.DoctorsInfoDataItem
import com.example.heal_me.databinding.DoctorsInfoItemViewBinding

class DoctorsInfoAdapter : ListAdapter<DoctorsInfoDataItem, DoctorsInfoAdapter.DoctorsInfoViewHolder>(DoctorsInfoAdapter.DATA_COMPARATOR){

    private companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<DoctorsInfoDataItem>() {
            override fun areItemsTheSame(
                oldItem: DoctorsInfoDataItem,
                newItem: DoctorsInfoDataItem
            ): Boolean = oldItem.doctorsInfoImage == newItem.doctorsInfoImage

            override fun areContentsTheSame(
                oldItem: DoctorsInfoDataItem,
                newItem: DoctorsInfoDataItem
            ): Boolean = oldItem == newItem

        }
    }

    class DoctorsInfoViewHolder(private val doctorsInfoItemViewBinding: DoctorsInfoItemViewBinding): RecyclerView.ViewHolder(doctorsInfoItemViewBinding.root){
        fun bind(doctorsInfoDataItem: DoctorsInfoDataItem){
            Glide.with(doctorsInfoItemViewBinding.root)
                .load(doctorsInfoDataItem.doctorsInfoImage)
                .into(doctorsInfoItemViewBinding.ivDoctorsInfoImage)
            doctorsInfoItemViewBinding.tvDoctorsInfoName.text = doctorsInfoDataItem.doctorsInfoName
            doctorsInfoItemViewBinding.tvDoctorsInfoSpeciality.text = doctorsInfoDataItem.doctorsInfoSpeciality
            doctorsInfoItemViewBinding.doctorsInfoDistance.text = doctorsInfoDataItem.doctorsInfoDistance
            doctorsInfoItemViewBinding.tvDoctorsInfoRatings.text = doctorsInfoDataItem.doctorsInfoRatings.toString()
            doctorsInfoItemViewBinding.tvDoctorsInfoReviews.text = doctorsInfoDataItem.doctorsInfoReviews.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsInfoViewHolder {
        return DoctorsInfoViewHolder(DoctorsInfoItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DoctorsInfoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}
