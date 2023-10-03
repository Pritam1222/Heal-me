package com.example.heal_me.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heal_me.R
import com.example.heal_me.adapters.NotificationAdapter
import com.example.heal_me.data.NotificationData
import com.example.heal_me.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    private lateinit var fragmentNotificationBinding : FragmentNotificationBinding
    private lateinit var notificationAdapter: NotificationAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentNotificationBinding = FragmentNotificationBinding.inflate(layoutInflater)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbar = fragmentNotificationBinding.toolbarNotification
        toolbar.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        notificationAdapter = NotificationAdapter()

        val notificationDataObject = listOf(
            NotificationData("Confirm Appointment","","Your appointment with Dr. Anna baker (Heart surgeon) at 11 Feb 2022 Time :  10:00 Am Address : Family Care Hospital, Golden Nest Phase 1 Rd, near Seven square academy, Golden Nest Phase 1, Queens Park, Mira Road, Maharashtra 401107","Your appointment with Dr. Anna baker (Heart surgeon) at 11 Feb 2022 Time :  10:00 Am Address : Family Care Hospital, Golden Nest Phase 1 Rd, near Seven square academy, Golden Nest Phase 1, Queens Park, Mira Road, Maharashtra 401107","02 mins ago"),
            NotificationData("Confirm Appointment","","Your appointment with Dr. Anna baker (Heart surgeon) at 11 Feb 2022 Time :  10:00 Am Address : Family Care Hospital, Golden Nest Phase 1 Rd, near Seven square academy, Golden Nest Phase 1, Queens Park, Mira Road, Maharashtra 401107","Your appointment with Dr. Anna baker (Heart surgeon) at 11 Feb 2022 Time :  10:00 Am Address : Family Care Hospital, Golden Nest Phase 1 Rd, near Seven square academy, Golden Nest Phase 1, Queens Park, Mira Road, Maharashtra 401107","02 mins ago"),
            NotificationData("Confirm Appointment","","Your appointment with Dr. Anna baker (Heart surgeon) at 11 Feb 2022 Time :  10:00 Am Address : Family Care Hospital, Golden Nest Phase 1 Rd, near Seven square academy, Golden Nest Phase 1, Queens Park, Mira Road, Maharashtra 401107","Your appointment with Dr. Anna baker (Heart surgeon) at 11 Feb 2022 Time :  10:00 Am Address : Family Care Hospital, Golden Nest Phase 1 Rd, near Seven square academy, Golden Nest Phase 1, Queens Park, Mira Road, Maharashtra 401107","02 mins ago"),
            NotificationData("Confirm Appointment","","Your appointment with Dr. Anna baker (Heart surgeon) at 11 Feb 2022 Time :  10:00 Am Address : Family Care Hospital, Golden Nest Phase 1 Rd, near Seven square academy, Golden Nest Phase 1, Queens Park, Mira Road, Maharashtra 401107","Your appointment with Dr. Anna baker (Heart surgeon) at 11 Feb 2022 Time :  10:00 Am Address : Family Care Hospital, Golden Nest Phase 1 Rd, near Seven square academy, Golden Nest Phase 1, Queens Park, Mira Road, Maharashtra 401107","02 mins ago"),
            NotificationData("Confirm Appointment","","Your appointment with Dr. Anna baker (Heart surgeon) at 11 Feb 2022 Time :  10:00 Am Address : Family Care Hospital, Golden Nest Phase 1 Rd, near Seven square academy, Golden Nest Phase 1, Queens Park, Mira Road, Maharashtra 401107","Your appointment with Dr. Anna baker (Heart surgeon) at 11 Feb 2022 Time :  10:00 Am Address : Family Care Hospital, Golden Nest Phase 1 Rd, near Seven square academy, Golden Nest Phase 1, Queens Park, Mira Road, Maharashtra 401107","02 mins ago"),
            NotificationData("Confirm Appointment","","Your appointment with Dr. Anna baker (Heart surgeon) at 11 Feb 2022 Time :  10:00 Am Address : Family Care Hospital, Golden Nest Phase 1 Rd, near Seven square academy, Golden Nest Phase 1, Queens Park, Mira Road, Maharashtra 401107","Your appointment with Dr. Anna baker (Heart surgeon) at 11 Feb 2022 Time :  10:00 Am Address : Family Care Hospital, Golden Nest Phase 1 Rd, near Seven square academy, Golden Nest Phase 1, Queens Park, Mira Road, Maharashtra 401107","02 mins ago")

        )
        notificationAdapter.submitList(notificationDataObject)

        fragmentNotificationBinding.rvNotification.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notificationAdapter
        }

        return fragmentNotificationBinding.root
    }

}