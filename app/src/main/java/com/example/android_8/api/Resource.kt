package com.example.android_8.api

import com.google.gson.annotations.SerializedName

data class Resource(
    val id: Int?,
    val name: String?,
    val year: Long?,
    val color: String?,

    @SerializedName("pantone_value")
    val pantoneValue: String?
)
