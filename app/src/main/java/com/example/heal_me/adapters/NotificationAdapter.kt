package com.example.heal_me.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heal_me.data.NotificationData
import com.example.heal_me.databinding.NotificationItemViewBinding
class NotificationAdapter : ListAdapter<NotificationData, NotificationAdapter.NotificationViewHolder>(NotificationAdapter.DATA_COMPARATOR){

    private companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<NotificationData>() {
            override fun areItemsTheSame(
                oldItem: NotificationData,
                newItem: NotificationData
            ): Boolean = oldItem.image == newItem.image

            override fun areContentsTheSame(
                oldItem: NotificationData,
                newItem: NotificationData
            ): Boolean = oldItem == newItem

        }
    }

    class NotificationViewHolder(private val notificationItemViewBinding: NotificationItemViewBinding): RecyclerView.ViewHolder(notificationItemViewBinding.root){

        val shortDescriptionNotification = notificationItemViewBinding.tvNotificationShortDescription
        val fullDescriptionNotification = notificationItemViewBinding.tvNotificationFullDescription

        fun bind(notificationDataItem: NotificationData){
            Glide.with(notificationItemViewBinding.root)
                .load(notificationDataItem.image)
                .into(notificationItemViewBinding.ivNotification)
            notificationItemViewBinding.tvNotificationTitle.text = notificationDataItem.title
            shortDescriptionNotification.text = notificationDataItem.shortDescription
            fullDescriptionNotification.text = notificationDataItem.fullDescription
            notificationItemViewBinding.tvNotificationTime.text = notificationDataItem.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationAdapter.NotificationViewHolder {
        return NotificationAdapter.NotificationViewHolder(
            NotificationItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotificationAdapter.NotificationViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }

        holder.itemView.setOnClickListener{
            holder.fullDescriptionNotification.visibility = if (holder.fullDescriptionNotification.visibility == View.VISIBLE) {
                View.GONE
            } else {
                View.VISIBLE
            }

            holder.shortDescriptionNotification.visibility = if (holder.shortDescriptionNotification.visibility == View.VISIBLE) {
                View.GONE
            } else {
                View.VISIBLE
            }

        }

    }
}