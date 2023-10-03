package com.example.heal_me.fragments

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentReferBinding

class ReferFragment : Fragment() {
    private lateinit var fragmentReferBinding: FragmentReferBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentReferBinding = FragmentReferBinding.inflate(layoutInflater)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbar = fragmentReferBinding.toolbarRefer
        toolbar.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        fragmentReferBinding.referCode.setOnClickListener {
            val textToCopy = "ABCDG123"
            copyTextToClipboard(textToCopy)
        }

        fragmentReferBinding.facebookIcon.setOnClickListener {
            val facebookPackage = "com.facebook.katana"
            val message = "Check out my awesome app: Heal-me - https://github.com/Pritam1222/Heal-me.git"

            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.`package` = facebookPackage
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, message)

            try {
                startActivity(sendIntent)
            } catch (e: ActivityNotFoundException) {
                // Handle the case where Facebook is not installed on the device
                // Provide appropriate user feedback or alternative actions
            }

        }

        fragmentReferBinding.whatsappIcon.setOnClickListener {
            val whatsappPackage = "com.whatsapp"
            val message = "Check out my awesome app: Heal-me - https://github.com/Pritam1222/Heal-me.git"

            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.`package` = whatsappPackage
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, message)

            try {
                startActivity(sendIntent)
            } catch (e: ActivityNotFoundException) {
                // Handle the case where WhatsApp is not installed on the device
                // You can display a message to the user or implement alternative actions
            }
        }

        fragmentReferBinding.instagramIcon.setOnClickListener{
            val instagramPackage = "com.instagram.android"
            val message = "Check out my awesome app: Heal-me - https://github.com/Pritam1222/Heal-me.git"

            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.`package` = instagramPackage
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, message)

            try {
                startActivity(sendIntent)
            } catch (e: ActivityNotFoundException) {
                // Handle the case where Instagram is not installed on the device
                // Provide appropriate user feedback or alternative actions
            }

        }


        fragmentReferBinding.telegramIcon.setOnClickListener{
            val telegramPackage = "org.telegram.messenger"
            val message = "Check out my awesome app: Heal-me - https://github.com/Pritam1222/Heal-me.git"

            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.`package` = telegramPackage
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, message)

            try {
                startActivity(sendIntent)
            } catch (e: ActivityNotFoundException) {
                // Handle the case where Telegram is not installed on the device
                // Provide appropriate user feedback or alternative actions
            }

        }

        return fragmentReferBinding.root
    }

    private fun copyTextToClipboard(text: String) {
        val clipboardManager = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text label", text)
        clipboardManager.setPrimaryClip(clipData)
        // Optionally, you can display a message indicating that the text has been copied
        Toast.makeText(requireContext(), "Text copied to clipboard", Toast.LENGTH_SHORT).show()
    }

}