package com.example.order

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient(Android) {
    install(ContentNegotiation) {
        json()
    }
}