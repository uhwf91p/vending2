package com.example.order.Server

import com.google.gson.annotations.SerializedName

data class ServerResponseData(
/*    @field:SerializedName("copyright") val copyright: String?,
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("explanation") val explanation: String?,
    @field:SerializedName("media_type") val mediaType: String?,
    @field:SerializedName("title") val title: String?,*/
    @field:SerializedName("name") val name: String,
    @field:SerializedName("id1") val id1: Int,///это строка для тестирования
    @field:SerializedName("id2") val id2: Int,///это строка для тестирования
   /* @field:SerializedName("hdurl") val hdurl: String?*/



)
