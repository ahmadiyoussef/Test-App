package com.example.testapp.data.models

import com.google.gson.annotations.SerializedName

data class Medicine(
    @SerializedName("name")
    val name: String,
    @SerializedName("dose")
    val dose: String,
    @SerializedName("strength")
    val strength: String
)
