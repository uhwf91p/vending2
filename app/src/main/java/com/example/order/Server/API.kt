package com.example.order.Server

import com.example.order.Data.ItemOfList
import retrofit2.Call
import retrofit2.http.*

interface API {

    @GET("GET")
    fun getDataFrom1C(

    ): Call<List<ServerResponseData>>

    @POST("SET")
    fun pullDataTo1C(@Body result:List<ItemOfList>):Call<List<ServerResponseData>>

    @GET("SomePlaceOnServer")//запрос списка завершенные нарядов - формируется на сервере путем сравнения проведенных нарядов в 1 с и высланных по методу pullDataTo1C нарядов
    fun getListOfFinishedOrders(

    ): Call<List<ServerResponseData>>



}