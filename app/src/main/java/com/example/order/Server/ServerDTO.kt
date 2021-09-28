package com.example.order.Server

import com.google.gson.annotations.SerializedName

data class ServerDTO (
@SerializedName("ВидОбъекта") val id1:String?,
@SerializedName("КОД") val id2:String?,
@SerializedName("КОД") val name:String?
)
/*
"ВидОбъекта": "ТранспортныеСредства",
"КОД": "8458ХЕ23",
"Наименование": "МТЗ-921, Гос.№8458ХЕ23, Хоз.№3047"*/
