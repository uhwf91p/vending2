package com.example.order.service

import com.example.order.infrastructure.SerialConnection

class SerialServiceImpl(
    private val serialConnection: SerialConnection
) : SerialService {

    override suspend fun connect(): Boolean {
        return serialConnection.connect()
    }

    override suspend fun disconnect(): Boolean {
        return serialConnection.disconnect()
    }

    override suspend fun writeString(data: String): Int {
        val byteArray = data.toByteArray()
        return serialConnection.write(byteArray)
    }
}