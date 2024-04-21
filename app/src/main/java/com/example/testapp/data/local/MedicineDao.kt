package com.example.testapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp.data.models.Medicine
import com.example.testapp.utils.Resource

@Dao
interface MedicineDao {
    @Query("SELECT * FROM Medicine")
    fun getAllMedicines(): List<MedicineEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(medicines: List<MedicineEntity>)
}