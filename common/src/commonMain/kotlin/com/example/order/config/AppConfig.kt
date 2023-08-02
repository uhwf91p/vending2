package com.example.order.config

import kotlinx.serialization.Serializable

// TODO: Сделать получение конфига из файла
@Serializable
data class AppConfig(
    val domain: String = "http://127.0.0.1:8080",
    val accessToken: String = "",
    val eofSymbol: String = "\n"
)
