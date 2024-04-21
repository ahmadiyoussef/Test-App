package com.example.testapp.data.models

import com.google.gson.annotations.SerializedName

data class Problems(
    @SerializedName("medicines")
    val medicines : List<Medicine>
)
