package com.example.testapp.data.remote

import com.example.testapp.data.models.Medicine
import retrofit2.http.GET

interface MedicineApi {
    @GET("e4cb6e92-4b13-4663-bedb-16359f86fc98")
    suspend fun getMedicines(): List<Medicine>
}