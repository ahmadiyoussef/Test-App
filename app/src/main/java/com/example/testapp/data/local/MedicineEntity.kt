package com.example.testapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testapp.data.models.Medicine

@Entity(tableName = "medicine")
data class MedicineEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val dose: String,
    val strength: String
)


fun MedicineEntity.toMedicine() = Medicine(
    name = name,
    dose = dose,
    strength = strength
)
