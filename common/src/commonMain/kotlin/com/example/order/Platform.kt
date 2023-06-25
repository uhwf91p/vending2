package com.example.order

import io.ktor.client.*

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient