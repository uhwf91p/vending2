package com.example.order

import com.example.order.config.AppConfig
import io.ktor.client.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.io.File

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient(Java) {
    install(ContentNegotiation) {
        json()
    }
}

actual fun appConfig(): AppConfig {
    val configPath = "./config.json"
    val text = File(configPath).readText()
    return Json.decodeFromString<AppConfig>(text)
}