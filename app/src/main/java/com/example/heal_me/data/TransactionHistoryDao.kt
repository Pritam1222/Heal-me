package com.example.heal_me.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TransactionHistoryDao {

    @Insert
    suspend fun insertTransaction(transactionHistory: TransactionHistory)

    @Delete
    suspend fun deleteTransaction(transactionHistory: TransactionHistory)

    @Update
    suspend fun updateTransaction(transactionHistory: TransactionHistory)

    @Query("SELECT * FROM transactionHistory")
    fun getTransactions() : LiveData<List<TransactionHistory>>

}