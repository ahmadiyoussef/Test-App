package com.example.testapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine")
data class MedicineEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val dose: String,
    val strength: String
)
