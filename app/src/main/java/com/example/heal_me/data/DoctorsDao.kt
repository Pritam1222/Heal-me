package com.example.heal_me.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DoctorsDao {

    @Insert
    suspend fun insertDoctor(doctors: Doctors)

    @Delete
    suspend fun deleteDoctor(doctors: Doctors)

    @Update
    suspend fun updateDoctor(doctors: Doctors)

    @Query("SELECT * FROM doctors")
    fun getDoctor() : LiveData<List<Doctors>>

    @Query("SELECT * FROM doctors WHERE specialist = :selectedSpecialty")
    fun getDoctorBySpecialty(selectedSpecialty: String) : LiveData<List<Doctors>>

    @Query("SELECT * FROM doctors WHERE id = :doctorId")
    fun getDoctorById(doctorId: Long) : LiveData<Doctors>

    @Query("SELECT * FROM doctors WHERE isFavourite = :isFavourite")
    fun getDoctorByFavourite(isFavourite: Boolean) : LiveData<List<Doctors>>


}