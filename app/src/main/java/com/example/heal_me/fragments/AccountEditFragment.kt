package com.example.heal_me.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentAccountEditBinding
import java.util.Calendar

class AccountEditFragment : Fragment() {
    private lateinit var fragmentAccountEditBinding: FragmentAccountEditBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAccountEditBinding = FragmentAccountEditBinding.inflate(layoutInflater)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbar = fragmentAccountEditBinding.toolbarAccountEdit
        toolbar.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        sharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        fragmentAccountEditBinding.clAccountEditSelectDate.setOnClickListener{
            val datePicker = fragmentAccountEditBinding.datePicker
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    val selectedDate = "$dayOfMonth-${monthOfYear + 1}-$year"
                    datePicker.visibility = View.VISIBLE
                    fragmentAccountEditBinding.accountEditDate.setText(selectedDate)
                    datePicker.visibility = View.GONE

                }, year, month, day
            )

            datePickerDialog.show()

        }

        val genders = arrayOf("Male", "Female", "Other")
        var selected = -1

        fragmentAccountEditBinding.clAccountEditSelectGender.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Select Gender")
            builder.setSingleChoiceItems(genders, selected) { _, which ->
                selected = which
            }
            builder.setPositiveButton("ok") { _, _ ->
                if (selected != -1 && selected < genders.size) {
                    fragmentAccountEditBinding.accountEditGenderText.setText(genders[selected])
                } else {
                    Toast.makeText(requireContext(), "Please select Gender", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("Cancel"){dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        var fullName = sharedPreferences.getString("name", "")
        var dateOfBirth = sharedPreferences.getString("dob", "")
        var email = sharedPreferences.getString("email", "")
        var gender = sharedPreferences.getString("gender", "")
        var height = sharedPreferences.getString("height", "")
        var weight = sharedPreferences.getString("weight", "")
        var address = sharedPreferences.getString("address", "")

        fragmentAccountEditBinding.accountEditFullName.setText(fullName.toString())
        fragmentAccountEditBinding.accountEditDate.setText(dateOfBirth.toString())
        fragmentAccountEditBinding.accountEditEmail.setText(email.toString())
        fragmentAccountEditBinding.accountEditGenderText.setText(gender.toString())
        fragmentAccountEditBinding.accountEditHeight.setText(height.toString())
        fragmentAccountEditBinding.accountEditWeight.setText(weight.toString())
        fragmentAccountEditBinding.accountEditAddress.setText(address.toString())

        val editor = sharedPreferences.edit()

        fragmentAccountEditBinding.buAccountEditSaveChanges.setOnClickListener {
            fullName = fragmentAccountEditBinding.accountEditFullName.text.toString()
            gender = fragmentAccountEditBinding.accountEditGenderText.text.toString()
            dateOfBirth =fragmentAccountEditBinding.accountEditDate.text.toString()
            height = fragmentAccountEditBinding.accountEditHeight.text.toString()
            weight = fragmentAccountEditBinding.accountEditWeight.text.toString()
            email = fragmentAccountEditBinding.accountEditEmail.text.toString()
            address = fragmentAccountEditBinding.accountEditAddress.text.toString()

            if (fullName!!.isEmpty() || gender!!.isEmpty() || dateOfBirth!!.isEmpty() || height!!.isEmpty() || weight!!.isEmpty() || email!!.isEmpty() || address!!.isEmpty()){
                Toast.makeText(activity, "Please fill the data", Toast.LENGTH_SHORT).show()
            } else {
                // Save the name and dob to SharedPreferences
                editor.putString("name", fullName)
                editor.putString("gender", gender)
                editor.putString("dob", dateOfBirth)
                editor.putString("height", height)
                editor.putString("weight", weight)
                editor.putString("email", email)
                editor.putString("address", address)
                editor.apply()

                findNavController().navigateUp()
            }

        }


        return fragmentAccountEditBinding.root
    }

}