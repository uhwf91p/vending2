package com.example.order.ui

data class MainState(
    val qrInput: String = "",
    val opened: Boolean = false,
    val input: String = "",
    val connectButtonText: String = "Подключить",
    val connectionState: ConnectionState = ConnectionState.Disconnected
)
