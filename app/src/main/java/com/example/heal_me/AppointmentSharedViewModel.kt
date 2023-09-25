package com.example.heal_me

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppointmentSharedViewModel : ViewModel() {
    private val _selectedAppointmentId = MutableLiveData<Long?>()
    val selectedAppointmentId: LiveData<Long?> = _selectedAppointmentId

    fun setSelectedAppointmentId(appointmentId: Long?) {
        _selectedAppointmentId.value = appointmentId
    }
}
