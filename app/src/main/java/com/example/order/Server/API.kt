package com.example.order.Server

import com.example.order.Data.Keys
import com.example.order.Data.MainList
import com.example.order.Room.LocalDataBase.ResultEntity
import retrofit2.Call
import retrofit2.http.*

interface API {

    @GET("GET")
    fun getDataFrom1C(




   /*     @Path("PL")
    @Query("Поля")apiKey2:String*/
       /* @Query("Тяжесть")hard:String,
        @Query("Бригада")brigade:String,
        @Query("Организация")organization:String,
        @Query("Профессия")profession:String*/
       /* @Query(Keys.DEFAULT_lIST)hard:List<MainList>*/
    ): Call<List<ServerResponseData>>

    @POST("SET")
    fun pullDataTo1C(@Body result:List<MainList>):Call<List<ServerResponseData>>

    @GET("SomePlaceOnServer")//запрос списка завершенные нарядов - формируется на сервере путем сравнения проведенных нарядов в 1 с и высланных по методу pullDataTo1C нарядов
    fun getListOfFinishedOrders(




        /*     @Path("PL")
         @Query("Поля")apiKey2:String*/
        /* @Query("Тяжесть")hard:String,
         @Query("Бригада")brigade:String,
         @Query("Организация")organization:String,
         @Query("Профессия")profession:String*/
        /* @Query(Keys.DEFAULT_lIST)hard:List<MainList>*/
    ): Call<List<ServerResponseData>>



}