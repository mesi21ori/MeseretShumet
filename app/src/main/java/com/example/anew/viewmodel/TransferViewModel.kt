package com.example.anew.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.anew.data.AppDatabase
import com.example.anew.data.Transfer
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TransferViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "app-database"
    )
        .fallbackToDestructiveMigration()
        .build()

    private val dao = db.transferDao()

    val transfers: StateFlow<List<Transfer>> = dao.getAllTransfers()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun insertTransfer(transfer: Transfer) {
        viewModelScope.launch {
            dao.insertTransfer(transfer)
        }
    }
}
