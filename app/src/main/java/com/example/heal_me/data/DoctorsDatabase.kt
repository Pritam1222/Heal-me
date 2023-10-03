package com.example.heal_me.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Doctors::class, Appointment::class, UploadDoc::class, TransactionHistory::class], version = 6)
abstract class DoctorsDatabase : RoomDatabase(){
    abstract fun doctorsDao() : DoctorsDao
    abstract fun appointmentDao() : AppointmentDao
    abstract fun uploadDocDao() : UploadDocDao
    abstract fun transactionHistoryDao() : TransactionHistoryDao

    companion object{
        @Volatile
        private var INSTANCE: DoctorsDatabase? = null

        private val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Add SQL statements here to update the schema for the new table
                database.execSQL(

                "CREATE TABLE IF NOT EXISTS transactionHistory (" +
                            "transactionId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL DEFAULT '0'," +
                            "transactionDate TEXT NULL," +
                            "transactionAmount REAL NULL," +
                            ")")
            }
        }

        fun getDatabase(context: Context): DoctorsDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DoctorsDatabase::class.java,
                        "doctorsDB").addMigrations(DoctorsDatabase.MIGRATION_5_6).build()
                }
            }
            return INSTANCE!!
        }
    }

}