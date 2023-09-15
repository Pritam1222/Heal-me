package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heal_me.R
import androidx.navigation.fragment.findNavController
import com.example.heal_me.adapters.MyRewardsAdapter
import com.example.heal_me.data.RewardsDataItem
import com.example.heal_me.databinding.FragmentRewardsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class RewardsFragment : Fragment() {

    private lateinit var myRewardsAdapter: MyRewardsAdapter
    private lateinit var fragmentRewardsBinding: FragmentRewardsBinding

    override fun onResume() {
        super.onResume()
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
            View.GONE
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentRewardsBinding= FragmentRewardsBinding.inflate(inflater, container, false)
        myRewardsAdapter = MyRewardsAdapter()

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbarMyRewards = fragmentRewardsBinding.toolbarMyRewards
        toolbarMyRewards.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbarMyRewards)

        toolbarMyRewards.setNavigationOnClickListener {
            findNavController().navigateUp()
        }


        val rewardsObject = mutableListOf<RewardsDataItem>()
        rewardsObject.add(RewardsDataItem("https://yt3.googleusercontent.com/FHQKP9GpufnM1A-xG9w7-sQviy_1e3YEpM1OvBzg9oGklTbnH1nIa_uEvtqfDV0J_R8WS15MkQ=s900-c-k-c0x00ffffff-no-rj","Gift card valued at 10% off at Puma.","Puma\n10% off coupon","04 Jan 2024","PUMA1234"))
        rewardsObject.add(RewardsDataItem("https://yt3.googleusercontent.com/ytc/AOPolaSbaST1JBNd9phht_n7tFN-VHx0FlvKPHeSDnmu4Q=s900-c-k-c0x00ffffff-no-rj","Gift card valued at 10% off at Netflix.","Netflix\n10% off coupon","04 Feb 2024","NETFLIX1234"))
        rewardsObject.add(RewardsDataItem("https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Oyorooms-branding.svg/1200px-Oyorooms-branding.svg.png","Gift card valued at 10% off at OYO.","OYO\n10% off coupon","04 sept 2024","OYO1234"))
        rewardsObject.add(RewardsDataItem("https://yt3.googleusercontent.com/ytc/AOPolaSOjYJxRcqYR-opWB-9zyTqS-KY3BlDj0gO1bCe2w=s900-c-k-c0x00ffffff-no-rj","Gift card valued at 10% off at McDonalds.","McDonalds\n10% off coupon","04 Aug 2024","MCDONALDS1234"))

        myRewardsAdapter.submitList(rewardsObject)
        fragmentRewardsBinding.rvMyRewards.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myRewardsAdapter
        }

        myRewardsAdapter.setOnItemClickListener(object : MyRewardsAdapter.OnItemClickListener {
            override fun onItemClick(rewardsDataItem: RewardsDataItem) {
                // Handle item click here

                // Pass data to the next fragment
                val bundle = Bundle()
                bundle.putString("image_url", rewardsDataItem.rewardsImage)
                bundle.putString("title", rewardsDataItem.rewardsTitle)
                bundle.putString("offer", rewardsDataItem.rewardsOffer)
                bundle.putString("expiry", rewardsDataItem.rewardsExpiry)
                bundle.putString("code", rewardsDataItem.rewardsCouponCode)

                // Navigate to the next fragment using the navigation graph
                findNavController().navigate(
                    R.id.action_rewardsFragment_to_selectedRewardFragment,
                    bundle
                )
                activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
                    View.GONE
            }
        })

        return fragmentRewardsBinding.root
    }

}