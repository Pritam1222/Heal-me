package com.example.heal_me.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctors")
data class Doctors(

    @PrimaryKey
    val id : Long,
    val name : String,
    val image : String,
    val specialist : String,
    val experience : String,
    val rating : Double,
    val patients : Long,
    val reviews : Long,
    val consultCharge : Long,
    var isFavourite : Boolean
)
