package com.example.heal_me.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "uploadDoc")
data class UploadDoc(

    @PrimaryKey(autoGenerate = true) val documentId : Long,
    @ColumnInfo(name = "patientName") val patientName : String,
    @ColumnInfo(name = "documentDate") val documentDate : Int,
    @ColumnInfo(name = "documentMonth") val documentMonth : String,
    @ColumnInfo(name = "documentCompleteDate") val documentCompleteDate : String,
    @ColumnInfo(name = "documentTime") val documentTime : String,
    @ColumnInfo(name = "documentUri") val documentUri : String,
    @ColumnInfo(name = "imageUri") val imageUri : String,
    @ColumnInfo(name = "documentType") val documentType : String
    
)