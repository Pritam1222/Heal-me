package com.example.heal_me.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointment")
data class Appointment(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "doctor_id") var doctorId: Long,
    @ColumnInfo(name = "doctor_name") var doctorName: String,
    @ColumnInfo(name = "doctor_image") var doctorImage: String,
    @ColumnInfo(name = "doctor_specialty") var doctorSpecialty: String,
    @ColumnInfo(name = "doctor_experience") var doctorExperience: String,
    @ColumnInfo(name = "appointment_type") var appointmentType: String,
    @ColumnInfo(name = "appointment_day") var appointmentDay: String,
    @ColumnInfo(name = "appointment_date") var appointmentDate: String,
    @ColumnInfo(name = "appointment_from_time") var appointmentFromTime: String,
    @ColumnInfo(name = "appointment_to_time") var appointmentToTime: String,
    @ColumnInfo(name = "doctor_patients") var doctorPatients: Long,
    @ColumnInfo(name = "doctor_reviews") var doctorReviews: Long,
    @ColumnInfo(name = "doctor_consult_charge") var doctorConsultCharge: Long,
    @ColumnInfo(name = "doctor_rating") var doctorRating: Double
)