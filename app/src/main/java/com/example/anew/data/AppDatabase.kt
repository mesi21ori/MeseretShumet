
package com.example.anew.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Transfer::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transferDao(): TransferDao
}
