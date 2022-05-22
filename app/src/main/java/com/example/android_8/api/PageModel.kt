package com.example.android_8.api

import com.google.gson.annotations.SerializedName

data class PageModel (
    val page: Int?,

    @SerializedName("per_page")
    val perPage: Int?,

    val total: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    val data: List<Resource>?
)