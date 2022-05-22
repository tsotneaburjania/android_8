package com.example.android_8.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ReqResApi {
    @GET("unknown")
    fun getResources(): Call<PageModel>

    @GET("unknown/{resourceId}")
    fun getResource(@Path("resourceId") resourceId: Int): Call<SingleResourceModel>
}