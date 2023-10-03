package com.example.heal_me.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentHelpSupportBinding

class HelpSupportFragment : Fragment() {

    private lateinit var fragmentHelpSupportBinding: FragmentHelpSupportBinding

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHelpSupportBinding = FragmentHelpSupportBinding.inflate(layoutInflater)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbarHelpSupport = fragmentHelpSupportBinding.toolbarHelpSupport
        toolbarHelpSupport.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbarHelpSupport)

        toolbarHelpSupport.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        fragmentHelpSupportBinding.sendMail.setOnClickListener {
            val email = "pritampatil1222@gmail.com"
            composeEmail(email)
        }

        return fragmentHelpSupportBinding.root
    }

    private fun composeEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$email")

        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "No email app found", Toast.LENGTH_SHORT).show()
        }
    }

}