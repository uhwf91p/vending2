package com.example.order.Server

import com.google.gson.annotations.SerializedName

data class ServerResponseData(



    @SerializedName("ВидОбъекта") var id1: String?,
    @SerializedName("КОД") var id2: String?,
    @SerializedName("Наименование") val name: String?


)
