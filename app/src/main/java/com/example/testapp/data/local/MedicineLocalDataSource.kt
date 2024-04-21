package com.example.testapp.data.local


import com.example.testapp.data.models.Medicine
import com.example.testapp.data.models.Problems
import com.example.testapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MedicineLocalDataSource @Inject constructor(
    private val medicineDao: MedicineDao
) {

    suspend fun getMedicineList(): List<Medicine> = withContext(Dispatchers.IO) {
        return@withContext medicineDao.getAllMedicines().map(MedicineEntity::toMedicine)
    }

    suspend fun saveMedicines(medicines: List<Medicine>) = withContext(Dispatchers.IO) {
        medicineDao.insertAll(medicines.map {
            MedicineEntity(
                id = 0,
                name = it.name,
                dose = it.dose,
                strength = it.strength
            )
        }

        )
    }
}