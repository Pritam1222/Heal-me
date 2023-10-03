package com.example.heal_me.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactionHistory")
data class TransactionHistory(

    @PrimaryKey(autoGenerate = true) val transactionId : Long,
    @ColumnInfo(name = "transactionDate") val transactionDate : String,
    @ColumnInfo(name = "transactionAmount") val transactionAmount : Double

)
