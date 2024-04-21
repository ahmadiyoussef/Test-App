package com.example.testapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapp.data.models.Medicine

@Database(entities = [Medicine::class], version = 1)
abstract class MedicineDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}