package com.example.order

import com.example.order.config.AppConfig
import io.ktor.client.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient(Java) {
    install(ContentNegotiation) {
        json()
    }
}

actual fun appConfig(): AppConfig {
    val resourcePath = "/config.json"
    val inputStream = AppConfig::class.java.getResourceAsStream(resourcePath)
        ?: throw IllegalArgumentException("Resource not found: $resourcePath")
    val jsonString = inputStream.bufferedReader().use { it.readText() }
    return Json.decodeFromString<AppConfig>(jsonString)
}