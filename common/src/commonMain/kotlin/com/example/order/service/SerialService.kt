package com.example.order.service

interface SerialService {
    suspend fun connect(): Boolean
    suspend fun disconnect(): Boolean
    suspend fun writeString(data: String): Int
}