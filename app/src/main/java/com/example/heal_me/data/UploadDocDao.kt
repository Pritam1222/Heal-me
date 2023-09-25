package com.example.heal_me.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UploadDocDao {
    @Insert
    suspend fun insertDocument(uploadDoc: UploadDoc)

    @Delete
    suspend fun deleteDocument(uploadDoc: UploadDoc)

    @Query("DELETE FROM uploadDoc WHERE documentId = :documentId")
    fun deleteDocumentById(documentId: Long)

    @Update
    suspend fun updateDocument(uploadDoc: UploadDoc)

    @Query("SELECT * FROM uploadDoc WHERE documentId = :documentId")
    fun getDocumentById(documentId: Long) : LiveData<UploadDoc>

    @Query("SELECT * FROM uploadDoc WHERE documentType = :selectedDocumentType")
    fun getDocumentByType(selectedDocumentType: String) : LiveData<List<UploadDoc>>

}