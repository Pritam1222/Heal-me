package com.example.heal_me.fragments

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.heal_me.R
import com.example.heal_me.data.Appointment
import com.example.heal_me.data.Doctors
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.data.TransactionHistory
import com.example.heal_me.databinding.FragmentDoctorDescriptionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DoctorDescriptionFragment : Fragment() {
    private lateinit var fragmentDoctorsDescriptionBinding : FragmentDoctorDescriptionBinding
    private lateinit var doctorsDatabase: DoctorsDatabase
    private lateinit var sharedPreferences: SharedPreferences
    private val args: DoctorDescriptionFragmentArgs by navArgs()

    lateinit var doctor : Doctors
    private var doctorId : Long = 0
    private var doctorName : String = ""
    private var doctorImage : String = ""
    private var doctorSpecialty : String = ""
    private var doctorExperience : String = ""
    private var appointmentType : String = "Upcoming"
    private var appointmentDay : String = ""
    private var appointmentDate : String = ""
    private var appointmentFromTime : String = ""
    private var appointmentToTime : String = ""
    private var doctorPatients : Long = 0
    private var doctorReviews : Long = 0
    private var doctorConsultCharge : Long = 0
    private var doctorRating : Double = 0.0
    private var isFavourite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDoctorsDescriptionBinding = FragmentDoctorDescriptionBinding.inflate(layoutInflater)

        sharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val whiteNavigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        whiteNavigationIcon.setTint(resources.getColor(R.color.white))

        val toolbar = fragmentDoctorsDescriptionBinding.toolbarDoctorDescription
        toolbar.navigationIcon = whiteNavigationIcon

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())
        doctorId = args.doctorId
        doctorsDatabase.doctorsDao().getDoctorById(doctorId).observe(viewLifecycleOwner, Observer { entity ->
            if (entity != null) {

                doctorName = entity.name
                doctorImage = entity.image
                doctorSpecialty = entity.specialist
                doctorExperience = entity.experience
                doctorPatients = entity.patients
                doctorReviews = entity.reviews
                doctorConsultCharge = entity.consultCharge
                doctorRating = entity.rating
                isFavourite = entity.isFavourite

                Glide.with(requireContext())
                    .load(entity.image)
                    .into(fragmentDoctorsDescriptionBinding.ivDoctorDescriptionImage)
                fragmentDoctorsDescriptionBinding.tvDoctorDescriptionName.text = entity.name
                fragmentDoctorsDescriptionBinding.tvDoctorDescriptionSpecialty.text = entity.specialist
                fragmentDoctorsDescriptionBinding.tvDoctorDescriptionPatients.text = entity.patients.toString()
                fragmentDoctorsDescriptionBinding.tvDoctorDescriptionExperience.text = entity.experience
                fragmentDoctorsDescriptionBinding.tvDoctorDescriptionRating.text = entity.rating.toString()
                fragmentDoctorsDescriptionBinding.tvDoctorDescriptionCharges.text = entity.consultCharge.toString()

                // Set the image resource based on the isFavourite value
                var newImageResource = if (isFavourite) {
                    R.drawable.ic_rating_star
                } else {
                    R.drawable.ic_rating_unselected_star
                }
                fragmentDoctorsDescriptionBinding.ivIsfavourite.setImageResource(newImageResource)

            } else {
                // Entity with the given ID not found
            }
        })

        val calendar = Calendar.getInstance()
        val currentCompleteDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Calendar.getInstance().time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay1.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate1.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay2.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate2.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay3.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate3.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay4.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate4.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay5.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate5.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay6.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate6.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay7.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate7.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)

        val dateCardViews = listOf(
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionDay1,
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionDay2,
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionDay3,
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionDay4,
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionDay5,
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionDay6,
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionDay7
        )

        val dayTextViews = listOf(
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay1,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay2,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay3,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay4,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay5,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay6,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDay7
        )

        val dateTextViews = listOf(
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate1,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate2,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate3,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate4,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate5,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate6,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionDate7
        )

        val timeCardViews = listOf(
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionTime1,
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionTime2,
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionTime3,
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionTime4,
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionTime5,
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionTime6,
            fragmentDoctorsDescriptionBinding.cvDoctorDescriptionTime7
        )

        val timeFromTextViews = listOf(
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionFromTime1,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionFromTime2,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionFromTime3,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionFromTime4,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionFromTime5,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionFromTime6,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionFromTime7
        )

        val timeToTextViews = listOf(
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionToTime1,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionToTime2,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionToTime3,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionToTime4,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionToTime5,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionToTime6,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionToTime7
        )

        val timeTextViews = listOf(
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionTime1,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionTime2,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionTime3,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionTime4,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionTime5,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionTime6,
            fragmentDoctorsDescriptionBinding.tvDoctorDescriptionTime7
        )

        var isDateSelected = false
        var isTimeSelected = false

        for (i in dateCardViews.indices) {
            dateCardViews[i].setOnClickListener {
                toggleDateCardViewColor(i, dateCardViews, dayTextViews, dateTextViews)
                isDateSelected = true
            }
        }

        for (i in timeCardViews.indices) {
            timeCardViews[i].setOnClickListener {
                toggleTimeCardViewColor(i, timeCardViews, timeFromTextViews, timeTextViews, timeToTextViews)
                isTimeSelected = true
            }
        }

        fragmentDoctorsDescriptionBinding.ivIsfavourite.setOnClickListener {
            isFavourite = !isFavourite

            var newImageResource = if (isFavourite) {
                R.drawable.ic_rating_star
            } else {
                R.drawable.ic_rating_unselected_star
            }
            fragmentDoctorsDescriptionBinding.ivIsfavourite.setImageResource(newImageResource)

            // Update the doctor's favorite status in the database
            updateDoctorFavoriteStatus(isFavourite)
        }

        val editor = sharedPreferences.edit()
        var walletCash = sharedPreferences.getString("wallet_cash", "")

        fragmentDoctorsDescriptionBinding.buDoctorDescriptionBookAppointment.setOnClickListener {

            if (walletCash.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Wallet cash not found", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val currentWalletCash = walletCash!!.toDouble()
                val newWalletCash = currentWalletCash - doctorConsultCharge.toDouble()

                if(doctorConsultCharge.toDouble() <= currentWalletCash){
                    // Update the wallet cash in shared preferences
                    editor.putString("wallet_cash", newWalletCash.toString()).apply()
                }
                if (!isDateSelected || !isTimeSelected) {
                    Toast.makeText(requireContext(), "Please schedule the appointment", Toast.LENGTH_SHORT).show()
                } else if(doctorConsultCharge.toDouble() > currentWalletCash){
                    Toast.makeText(requireContext(), "Insufficient Wallet Balance", Toast.LENGTH_SHORT).show()
                }else {
                    GlobalScope.launch {
                        try {
                            doctorsDatabase.appointmentDao().insertAppointment(Appointment(0, doctorId, doctorName, doctorImage, doctorSpecialty, doctorExperience, appointmentType, appointmentDay, appointmentDate, appointmentFromTime, appointmentToTime, doctorPatients, doctorReviews, doctorConsultCharge, doctorRating))
                            doctorsDatabase.transactionHistoryDao().insertTransaction(TransactionHistory(0, currentCompleteDate, doctorConsultCharge.toDouble()))
                            withContext(Dispatchers.Main) {
                                // Navigate on the main thread
                                findNavController().navigateUp()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            // Log or handle the exception as needed
                        }
                    }
                }
            } catch (e: NumberFormatException) {
                // Handle the case where walletCash or doctorConsultCharge cannot be parsed as a double
                Toast.makeText(requireContext(), "Invalid wallet cash or doctor charge", Toast.LENGTH_SHORT).show()
            }
        }

        return fragmentDoctorsDescriptionBinding.root
    }

    private fun toggleDateCardViewColor(index: Int, cardViews: List<CardView>, textView1: List<TextView>, textView2: List<TextView>) {
        val defaultColor = Color.parseColor("#089BAB")

        for (i in cardViews.indices) {
            if (i == index) {
                cardViews[i].setCardBackgroundColor(defaultColor)
                textView1[i].setTextColor(Color.WHITE)
                textView2[i].setTextColor(Color.WHITE)
                appointmentDay = textView1[i].text.toString()
                appointmentDate = textView2[i].text.toString()
            } else {
                cardViews[i].setCardBackgroundColor(Color.WHITE)
                textView1[i].setTextColor(Color.BLACK)
                textView2[i].setTextColor(Color.BLACK)
            }
        }
    }

    private fun updateDoctorFavoriteStatus(isFavorite: Boolean) {
        doctorsDatabase.doctorsDao().getDoctorById(doctorId).observe(viewLifecycleOwner, Observer { fetchedDoctor ->
            if (fetchedDoctor != null) {
                doctor = fetchedDoctor
                doctor.isFavourite = isFavorite

                GlobalScope.launch {
                    try {
                        doctorsDatabase.doctorsDao().updateDoctor(doctor)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        // Handle the exception as needed
                    }
                }
            } else {
                // Handle the case where the doctor with the given ID is not found
            }
        })
    }

    private fun toggleTimeCardViewColor(index: Int, cardViews: List<CardView>, textView1: List<TextView>, textView2: List<TextView>, textView3: List<TextView>) {
        val defaultColor = Color.parseColor("#089BAB")

        for (i in cardViews.indices) {
            if (i == index) {
                cardViews[i].setCardBackgroundColor(defaultColor)
                textView1[i].setTextColor(Color.WHITE)
                textView2[i].setTextColor(Color.WHITE)
                textView3[i].setTextColor(Color.WHITE)
                appointmentFromTime = textView1[i].text.toString()
                appointmentToTime = textView3[i].text.toString()
            } else {
                cardViews[i].setCardBackgroundColor(Color.WHITE)
                textView1[i].setTextColor(Color.BLACK)
                textView2[i].setTextColor(Color.BLACK)
                textView3[i].setTextColor(Color.BLACK)
            }
        }
    }

}