package com.example.testapp.data.remote

import com.example.testapp.data.models.Medicine
import com.example.testapp.data.models.Problems
import retrofit2.http.GET

interface MedicineApi {
    @GET("eb33b6cf-48ba-43ca-ba61-84b8b8e0d988")
    suspend fun getMedicines(): Problems
}