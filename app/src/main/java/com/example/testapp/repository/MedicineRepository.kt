package com.example.testapp.repository

import com.example.testapp.data.local.MedicineDao
import com.example.testapp.data.local.MedicineLocalDataSource
import com.example.testapp.data.models.Medicine
import com.example.testapp.data.remote.MedicineApi
import com.example.testapp.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
@ActivityScoped
class MedicineRepository @Inject constructor(
    private val localDataSource: MedicineLocalDataSource,
    private val api: MedicineApi
) {
    suspend fun getMedicineList(): List<Medicine>{
        val localMedicines = localDataSource.getMedicineList()
        if (localMedicines.isNotEmpty()) return localMedicines

        val remoteMedicines = api.getMedicines()
        localDataSource.saveMedicines(remoteMedicines.medicines)

        return localDataSource.getMedicineList()
    }
}