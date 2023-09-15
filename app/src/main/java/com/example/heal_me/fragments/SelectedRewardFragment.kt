package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentRewardsBinding
import com.example.heal_me.databinding.FragmentSelectedRewardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SelectedRewardFragment : Fragment() {

    private lateinit var fragmentSelectedRewardsBinding: FragmentSelectedRewardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSelectedRewardsBinding= FragmentSelectedRewardBinding.inflate(inflater, container, false)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val selectedRewardToolbar = fragmentSelectedRewardsBinding.selectedRewardToolbar
        selectedRewardToolbar.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(selectedRewardToolbar)

        selectedRewardToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()

        }


        // Retrieve arguments
        val imageUrl = arguments?.getString("image_url")
        val offer = arguments?.getString("offer")
        val expiryDate = arguments?.getString("expiry")
        val couponCode = arguments?.getString("code")

        imageUrl?.let {
            Glide.with(requireContext())
                .load(it)
                .into(fragmentSelectedRewardsBinding.ivSelectedReward)
        }
        fragmentSelectedRewardsBinding.tvRewardOffer.text = offer
        fragmentSelectedRewardsBinding.rewardExpiresDate.text = expiryDate
        fragmentSelectedRewardsBinding.rewardCouponCode.text = couponCode


        return fragmentSelectedRewardsBinding.root
    }

}