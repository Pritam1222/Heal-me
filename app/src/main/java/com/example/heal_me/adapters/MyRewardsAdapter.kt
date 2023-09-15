package com.example.heal_me.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heal_me.data.RewardsDataItem
import com.example.heal_me.databinding.MyRewardItemViewBinding

class MyRewardsAdapter : ListAdapter<RewardsDataItem, MyRewardsAdapter.MyRewardsViewHolder>(DATA_COMPARATOR) {

    private companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<RewardsDataItem>() {
            override fun areItemsTheSame(
                oldItem: RewardsDataItem,
                newItem: RewardsDataItem
            ): Boolean = oldItem.rewardsImage == newItem.rewardsImage

            override fun areContentsTheSame(
                oldItem: RewardsDataItem,
                newItem: RewardsDataItem
            ): Boolean = oldItem == newItem
        }
    }

    // Interface to handle item clicks
    interface OnItemClickListener {
        fun onItemClick(rewardsDataItem: RewardsDataItem)
    }

    private var itemClickListener: OnItemClickListener? = null

    // Set the click listener from outside the adapter
    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class MyRewardsViewHolder(private val myRewardItemViewBinding: MyRewardItemViewBinding, private val adapter: MyRewardsAdapter) :
        RecyclerView.ViewHolder(myRewardItemViewBinding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val rewardsDataItem = adapter.getItem(position)
                    rewardsDataItem?.let {
                        adapter.itemClickListener?.onItemClick(it)
                    }
                }
            }
        }


        fun bind(rewardsDataItem: RewardsDataItem) {
            Glide.with(myRewardItemViewBinding.root)
                .load(rewardsDataItem.rewardsImage)
                .into(myRewardItemViewBinding.ivMyRewardIcon)
            myRewardItemViewBinding.rewardTitle.text = rewardsDataItem.rewardsTitle
            myRewardItemViewBinding.tvExpiryDate.text = rewardsDataItem.rewardsExpiry
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRewardsViewHolder {
        return MyRewardsViewHolder(
            MyRewardItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            this // Pass the adapter instance to the ViewHolder constructor
        )
    }

    override fun onBindViewHolder(holder: MyRewardsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}
