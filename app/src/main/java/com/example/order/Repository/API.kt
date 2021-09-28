package com.example.order.Repository

import com.example.order.Server.ServerResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("adMobileExchange")
    suspend fun getDataFrom1C(@Query("ТранспортныеСредства")apiKey:String): Response<ServerResponseData>




}