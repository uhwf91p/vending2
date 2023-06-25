package com.example.order

import io.ktor.client.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient(Java) {
    install(ContentNegotiation) {
        json()
    }
}