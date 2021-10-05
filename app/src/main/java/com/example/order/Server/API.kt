package com.example.order.Server

import com.example.order.Server.ServerResponseData
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("adMobileExchange")
    fun getDataFrom1C(@Query("ТранспортныеСредства")apiKey:String): Call<List<ServerResponseData>>


}