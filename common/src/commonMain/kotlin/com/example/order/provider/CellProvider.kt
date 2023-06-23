package com.example.order.provider

import com.example.order.dto.CheckCodeRequest
import com.example.order.dto.CheckCodeResponse
import com.example.order.dto.SwitchCellRequest

interface CellProvider {
    suspend fun checkCode(checkCodeRequest: CheckCodeRequest): CheckCodeResponse

    suspend fun switchCell(switchCellRequest: SwitchCellRequest)
}