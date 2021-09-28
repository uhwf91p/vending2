package com.example.order.Server

import com.google.gson.annotations.SerializedName

data class ServerResponseData(


    @SerializedName("ТранспортныеСредства") val listFromServer:List<ServerDTO>






)
