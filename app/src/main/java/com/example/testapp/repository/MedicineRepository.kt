package com.example.testapp.repository

import com.example.testapp.data.models.Medicine
import com.example.testapp.data.models.Problems
import com.example.testapp.data.remote.MedicineApi
import com.example.testapp.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
@ActivityScoped
class MedicineRepository @Inject constructor(
    private val api : MedicineApi
) {

    suspend fun getMedicineList(): Resource<Problems> {
        val response = try {
            api.getMedicines()
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }
}