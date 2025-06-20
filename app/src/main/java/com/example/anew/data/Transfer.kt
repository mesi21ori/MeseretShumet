package com.example.anew.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "transfers")
data class Transfer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val bankName: String,
    val accountNumber: String,
    val amount: Int,
    val status: String = "Success",
    val timestamp: Long = System.currentTimeMillis()
): Serializable
