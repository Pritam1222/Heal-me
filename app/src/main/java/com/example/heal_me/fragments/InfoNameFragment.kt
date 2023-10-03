package com.example.heal_me.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.heal_me.R
import com.example.heal_me.databinding.FragmentInfoNameBinding
import java.util.*
import android.content.SharedPreferences
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity


class InfoNameFragment : Fragment() {

    private var _binding: FragmentInfoNameBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoNameBinding.inflate(inflater, container, false)

        sharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)


        val button: ImageButton = binding.selectDateButton
        button.setOnClickListener{
            val datePicker = binding.datePicker
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val selectedDateEditText = binding.tvDate

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    val selectedDate = "$dayOfMonth-${monthOfYear + 1}-$year"
                    datePicker.visibility = View.VISIBLE
                    selectedDateEditText.setText(selectedDate)
                    datePicker.visibility = View.GONE

                }, year, month, day
            )

            datePickerDialog.show()

        }


        val conditionText = SpannableStringBuilder(binding.conditionText.text.toString())
        val fColor = ForegroundColorSpan(Color.rgb(8, 155, 171))
        conditionText.setSpan(fColor,35,42, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.conditionText.text = conditionText

        val termsAndConditions = SpannableString(binding.termsConditions.text.toString())
        val underlineSpan = UnderlineSpan()
        termsAndConditions.setSpan(underlineSpan,0,16, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        binding.termsConditions.text = termsAndConditions
        binding.termsConditions.setOnClickListener {
            it.findNavController().navigate(R.id.action_infoNameFragment_to_termsConditionsFragment)
        }

        binding.fullName.imeOptions = EditorInfo.IME_ACTION_DONE

        val editor = sharedPreferences.edit()

        binding.nameDob.setOnClickListener {
            val fullName = binding.fullName.text.toString()
            val dateOfBirth = binding.tvDate.text.toString()

            Log.i("Info Name Fragment", fullName)
            Log.i("Info Name Fragment", dateOfBirth)

            if (fullName.isEmpty()){
                Toast.makeText(activity, "Please fill the data", Toast.LENGTH_SHORT).show()
            } else if (dateOfBirth.isEmpty()){
                Toast.makeText(activity, "Please fill the data", Toast.LENGTH_SHORT).show()
            } else {
                // Save the name and dob to SharedPreferences
                editor.putString("name", fullName)
                editor.putString("dob", dateOfBirth)
                editor.apply()


                findNavController().navigate(R.id.action_infoNameFragment_to_infoEmailFragment)
            }
        }



        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}