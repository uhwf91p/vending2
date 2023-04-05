package com.example.order.ui

sealed class ConnectionState {
    object Disconnected : ConnectionState()
    object Connected : ConnectionState()
}