package com.example.heal_me.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Doctors::class, Appointment::class, UploadDoc::class], version = 5)
abstract class DoctorsDatabase : RoomDatabase(){
    abstract fun doctorsDao() : DoctorsDao
    abstract fun appointmentDao() : AppointmentDao
    abstract fun uploadDocDao() : UploadDocDao

    companion object{
        @Volatile
        private var INSTANCE: DoctorsDatabase? = null

        private val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Add SQL statements here to update the schema for the new table
                database.execSQL(

                "CREATE TABLE IF NOT EXISTS uploadDoc (" +
                            "documentId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL DEFAULT '0'," +
                            "patientName TEXT NULL," +
                            "documentDate INTEGER NULL," +
                            "documentMonth TEXT NULL," +
                            "documentCompleteDate TEXT NULL," +
                            "documentTime TEXT NULL," +
                            "documentUri TEXT NULL," +
                            "imageUri TEXT NULL," +
                            "documentType TEXT NULL," +
                            ")")
            }
        }

        fun getDatabase(context: Context): DoctorsDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DoctorsDatabase::class.java,
                        "doctorsDB").addMigrations(DoctorsDatabase.MIGRATION_4_5).build()
                }
            }
            return INSTANCE!!
        }
    }

}