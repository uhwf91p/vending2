package com.example.order

import com.example.order.config.AppConfig
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient(Android) {
    install(ContentNegotiation) {
        json()
    }
}

actual fun appConfig() = AppConfig()