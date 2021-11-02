package com.example.order.Server

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("adMobileExchange")
    fun getDataFrom1C(
        @Query("ТранспортныеСредства")apiKey:String,
     /*   @Query("Бригадир")brigadier:String,
        @Query("Тяжесть")hard:String,
        @Query("Бригада")brigade:String,
        @Query("Организация")organization:String,
        @Query("Профессия")profession:String*/
    ): Call<List<ServerResponseData>>



}