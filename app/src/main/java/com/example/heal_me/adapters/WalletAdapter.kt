package com.example.heal_me.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heal_me.data.Doctors
import com.example.heal_me.data.TransactionHistory
import com.example.heal_me.databinding.DoctorsInfoItemViewBinding
import com.example.heal_me.databinding.TransactionHistoryItemViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class WalletAdapter() : ListAdapter<TransactionHistory, WalletAdapter.WalletViewHolder>(WalletAdapter.DATA_COMPARATOR) {

    private lateinit var navController : NavController

    private companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<TransactionHistory>() {
            override fun areItemsTheSame(
                oldItem: TransactionHistory,
                newItem: TransactionHistory
            ): Boolean = oldItem.transactionId == newItem.transactionId

            override fun areContentsTheSame(
                oldItem: TransactionHistory,
                newItem: TransactionHistory
            ): Boolean = oldItem == newItem

        }
    }

    class WalletViewHolder(private val transactionHistoryItemViewBinding: TransactionHistoryItemViewBinding): RecyclerView.ViewHolder(transactionHistoryItemViewBinding.root){
        fun bind(transactionHistory: TransactionHistory){

            transactionHistoryItemViewBinding.transactionDate.text = transactionHistory.transactionDate
            transactionHistoryItemViewBinding.transactionAmount.text = transactionHistory.transactionAmount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletAdapter.WalletViewHolder {
        return WalletAdapter.WalletViewHolder(
            TransactionHistoryItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WalletAdapter.WalletViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }


}