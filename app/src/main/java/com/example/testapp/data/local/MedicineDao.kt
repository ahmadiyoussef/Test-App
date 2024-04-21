package com.example.testapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp.data.models.Medicine

@Dao
interface MedicineDao {
    @Query("SELECT * FROM Medicine")
    fun getAllMedicines(): LiveData<List<Medicine>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(medicines: List<Medicine>)
}