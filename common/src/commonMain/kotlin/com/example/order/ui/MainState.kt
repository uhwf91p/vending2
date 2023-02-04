package com.example.order.ui

data class MainState(
    val input: String = "",
    val connectButtonText: String = "Подключить",
    val connectionState: ConnectionState = ConnectionState.Disconnected
)
