package com.example.heal_me.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heal_me.R
import com.example.heal_me.adapters.WalletAdapter
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.databinding.FragmentWalletBinding

class WalletFragment : Fragment() {
    private lateinit var fragmentWalletBinding: FragmentWalletBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var doctorsDatabase: DoctorsDatabase
    private lateinit var walletAdapter: WalletAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentWalletBinding = FragmentWalletBinding.inflate(layoutInflater)

        sharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbar = fragmentWalletBinding.toolbarWallet
        toolbar.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val walletCash = sharedPreferences.getString("wallet_cash", "")

        fragmentWalletBinding.walletCash.text = walletCash

        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())

        walletAdapter = WalletAdapter()

        doctorsDatabase.transactionHistoryDao().getTransactions().observe(viewLifecycleOwner) { data ->
            walletAdapter.submitList(data)
        }

        fragmentWalletBinding.rvWalletTransaction.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = walletAdapter
        }

        return fragmentWalletBinding.root
    }

}