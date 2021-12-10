package com.example.order.Server

import com.example.order.Data.MainList
import com.example.order.Room.LocalDataBase.ResultEntity
import retrofit2.Call
import retrofit2.http.*

interface API {

    @GET("adMobileExchange")
    fun getDataFrom1C(




   /*     @Path("PL")
    @Query("Поля")apiKey2:String*/
       /* @Query("Тяжесть")hard:String,
        @Query("Бригада")brigade:String,
        @Query("Организация")organization:String,
        @Query("Профессия")profession:String*/
    ): Call<List<ServerResponseData>>

    @POST("adMobileExchange")
    fun pullDataTo1C(@Body result:List<MainList>):Call<List<ServerResponseData>>



}