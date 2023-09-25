package com.example.heal_me.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.heal_me.data.Appointment
import com.example.heal_me.data.DoctorsDatabase
import com.example.heal_me.databinding.FragmentRescheduleAppointmentBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RescheduleAppointmentFragment : Fragment() {
    private lateinit var fragmentRescheduleAppointmentBinding: FragmentRescheduleAppointmentBinding
    private lateinit var doctorsDatabase: DoctorsDatabase
    private val args: RescheduleAppointmentFragmentArgs by navArgs()

    lateinit var appointment : Appointment

    private var appointmentId : Long = 0
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentRescheduleAppointmentBinding = FragmentRescheduleAppointmentBinding.inflate(layoutInflater)

        doctorsDatabase = DoctorsDatabase.getDatabase(requireContext())
        appointmentId = args.doctorId

        doctorsDatabase.appointmentDao().getAppointmentById(appointmentId).observe(viewLifecycleOwner, Observer { entity ->
            if (entity != null) {

                doctorName = entity.doctorName
                doctorImage = entity.doctorImage
                doctorSpecialty = entity.doctorSpecialty
                doctorExperience = entity.doctorExperience
                doctorPatients = entity.doctorPatients
                doctorReviews = entity.doctorReviews
                doctorConsultCharge = entity.doctorConsultCharge
                doctorRating = entity.doctorRating

                Glide.with(requireContext())
                    .load(entity.doctorImage)
                    .into(fragmentRescheduleAppointmentBinding.ivRescheduleImage)
                fragmentRescheduleAppointmentBinding.tvRescheduleName.text = entity.doctorName
                fragmentRescheduleAppointmentBinding.tvRescheduleSpecialty.text = entity.doctorSpecialty
            } else {
                // Entity with the given ID not found
            }
        })

        val calendar = Calendar.getInstance()
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentRescheduleAppointmentBinding.tvRescheduleDay1.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentRescheduleAppointmentBinding.tvRescheduleDate1.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentRescheduleAppointmentBinding.tvRescheduleDay2.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentRescheduleAppointmentBinding.tvRescheduleDate2.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentRescheduleAppointmentBinding.tvRescheduleDay3.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentRescheduleAppointmentBinding.tvRescheduleDate3.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentRescheduleAppointmentBinding.tvRescheduleDay4.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentRescheduleAppointmentBinding.tvRescheduleDate4.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentRescheduleAppointmentBinding.tvRescheduleDay5.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentRescheduleAppointmentBinding.tvRescheduleDate5.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentRescheduleAppointmentBinding.tvRescheduleDay6.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentRescheduleAppointmentBinding.tvRescheduleDate6.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        fragmentRescheduleAppointmentBinding.tvRescheduleDay7.text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time)
        fragmentRescheduleAppointmentBinding.tvRescheduleDate7.text = SimpleDateFormat("MMMM d", Locale.getDefault()).format(calendar.time)
        // Move the calendar to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1)

        val dateCardViews = listOf(
            fragmentRescheduleAppointmentBinding.cvRescheduleDay1,
            fragmentRescheduleAppointmentBinding.cvRescheduleDay2,
            fragmentRescheduleAppointmentBinding.cvRescheduleDay3,
            fragmentRescheduleAppointmentBinding.cvRescheduleDay4,
            fragmentRescheduleAppointmentBinding.cvRescheduleDay5,
            fragmentRescheduleAppointmentBinding.cvRescheduleDay6,
            fragmentRescheduleAppointmentBinding.cvRescheduleDay7
        )

        val dayTextViews = listOf(
            fragmentRescheduleAppointmentBinding.tvRescheduleDay1,
            fragmentRescheduleAppointmentBinding.tvRescheduleDay2,
            fragmentRescheduleAppointmentBinding.tvRescheduleDay3,
            fragmentRescheduleAppointmentBinding.tvRescheduleDay4,
            fragmentRescheduleAppointmentBinding.tvRescheduleDay5,
            fragmentRescheduleAppointmentBinding.tvRescheduleDay6,
            fragmentRescheduleAppointmentBinding.tvRescheduleDay7
        )

        val dateTextViews = listOf(
            fragmentRescheduleAppointmentBinding.tvRescheduleDate1,
            fragmentRescheduleAppointmentBinding.tvRescheduleDate2,
            fragmentRescheduleAppointmentBinding.tvRescheduleDate3,
            fragmentRescheduleAppointmentBinding.tvRescheduleDate4,
            fragmentRescheduleAppointmentBinding.tvRescheduleDate5,
            fragmentRescheduleAppointmentBinding.tvRescheduleDate6,
            fragmentRescheduleAppointmentBinding.tvRescheduleDate7
        )

        val timeCardViews = listOf(
            fragmentRescheduleAppointmentBinding.cvRescheduleTime1,
            fragmentRescheduleAppointmentBinding.cvRescheduleTime2,
            fragmentRescheduleAppointmentBinding.cvRescheduleTime3,
            fragmentRescheduleAppointmentBinding.cvRescheduleTime4,
            fragmentRescheduleAppointmentBinding.cvRescheduleTime5,
            fragmentRescheduleAppointmentBinding.cvRescheduleTime6,
            fragmentRescheduleAppointmentBinding.cvRescheduleTime7
        )

        val timeFromTextViews = listOf(
            fragmentRescheduleAppointmentBinding.tvRescheduleFromTime1,
            fragmentRescheduleAppointmentBinding.tvRescheduleFromTime2,
            fragmentRescheduleAppointmentBinding.tvRescheduleFromTime3,
            fragmentRescheduleAppointmentBinding.tvRescheduleFromTime4,
            fragmentRescheduleAppointmentBinding.tvRescheduleFromTime5,
            fragmentRescheduleAppointmentBinding.tvRescheduleFromTime6,
            fragmentRescheduleAppointmentBinding.tvRescheduleFromTime7
        )

        val timeToTextViews = listOf(
            fragmentRescheduleAppointmentBinding.tvRescheduleToTime1,
            fragmentRescheduleAppointmentBinding.tvRescheduleToTime2,
            fragmentRescheduleAppointmentBinding.tvRescheduleToTime3,
            fragmentRescheduleAppointmentBinding.tvRescheduleToTime4,
            fragmentRescheduleAppointmentBinding.tvRescheduleToTime5,
            fragmentRescheduleAppointmentBinding.tvRescheduleToTime6,
            fragmentRescheduleAppointmentBinding.tvRescheduleToTime7
        )

        val timeTextViews = listOf(
            fragmentRescheduleAppointmentBinding.tvRescheduleTime1,
            fragmentRescheduleAppointmentBinding.tvRescheduleTime2,
            fragmentRescheduleAppointmentBinding.tvRescheduleTime3,
            fragmentRescheduleAppointmentBinding.tvRescheduleTime4,
            fragmentRescheduleAppointmentBinding.tvRescheduleTime5,
            fragmentRescheduleAppointmentBinding.tvRescheduleTime6,
            fragmentRescheduleAppointmentBinding.tvRescheduleTime7
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

        fragmentRescheduleAppointmentBinding.buRescheduleConfirmAppointment.setOnClickListener {
            if (!isDateSelected || !isTimeSelected) {
                Toast.makeText(requireContext(), "Please schedule the appointment", Toast.LENGTH_SHORT).show()
            } else {
                // Add the code here to initialize and update the appointment
                // Retrieve the appointment using its ID or any other unique identifier
                // Update its properties as needed
                // Then update it in the database and navigate

                doctorsDatabase.appointmentDao().getAppointmentById(appointmentId).observe(viewLifecycleOwner, Observer { fetchedAppointment ->
                    if (fetchedAppointment != null) {
                        appointment = fetchedAppointment
                        // Now you have the appointment object with the correct data
                        // You can update its properties as needed
                        appointment.appointmentType = "Upcoming"

                        // Update other appointment properties if needed

                        GlobalScope.launch {
                            try {
                                doctorsDatabase.appointmentDao().updateAppointment(appointment)
                            } catch (e: Exception) {
                                e.printStackTrace()
                                // Handle the exception as needed
                            }
                        }
                        findNavController().navigateUp()
                    } else {
                        // Handle the case where the appointment with the given ID is not found
                    }
                })
            }
        }


        return fragmentRescheduleAppointmentBinding.root
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