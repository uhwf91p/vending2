package com.example.order.service

interface CellService {
    suspend fun openCellByCode(code: String)
}