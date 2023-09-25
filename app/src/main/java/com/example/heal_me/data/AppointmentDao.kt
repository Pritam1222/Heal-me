package com.example.heal_me.data
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppointmentDao {
    @Insert
    suspend fun insertAppointment(appointment: Appointment)

    @Delete
    suspend fun deleteAppointment(appointment: Appointment)

    @Update
    suspend fun updateAppointment(appointment: Appointment)

    @Query("SELECT * FROM appointment")
    fun getAppointment() : LiveData<List<Appointment>>

    @Query("SELECT * FROM appointment WHERE appointment_type = :selectedType")
    fun getAppointmentByType(selectedType: String) : LiveData<List<Appointment>>

    @Query("SELECT * FROM appointment WHERE id = :selectedId")
    fun getAppointmentById(selectedId: Long) : LiveData<Appointment>

}