package com.example.testapp.data.local



import androidx.lifecycle.LiveData
import com.example.testapp.data.models.Medicine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MedicineLocalDataSource @Inject constructor(
    private val medicineDao: MedicineDao
) {

    suspend fun getCarList(): LiveData<List<Medicine>> = withContext(Dispatchers.IO) {
        return@withContext medicineDao.getAllMedicines()
    }

    suspend fun saveCars(medicines: List<Medicine>) = withContext(Dispatchers.IO) {
        medicineDao.insertAll( medicines.map {
            Medicine(
                name = it.name,
                dose = it.dose,
                strength = it.strength
            )
        }

        )
    }
}