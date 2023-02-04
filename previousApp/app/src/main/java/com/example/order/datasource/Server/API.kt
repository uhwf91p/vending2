package com.example.order.datasource.Server

import com.example.order.app.domain.model.ListItem
import retrofit2.Call
import retrofit2.http.*

interface API {

    @GET("GET")
    fun getDataFrom1C(

    ): Call<List<ServerResponseData>>

    @POST("SET")
    fun pullDataTo1C(@Body result:List<ListItem>):Call<List<ServerResponseData>>





}