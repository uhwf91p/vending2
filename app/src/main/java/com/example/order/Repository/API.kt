package com.example.order.Repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface API {
    @GET("planetary/apod")
    fun getDataFrom1C(@Query("api_key")apiKey:String): Call<ServerResponseData>




}