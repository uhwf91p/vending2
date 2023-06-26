package com.example.order

import com.example.order.config.AppConfig
import io.ktor.client.*

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

expect fun appConfig(): AppConfig