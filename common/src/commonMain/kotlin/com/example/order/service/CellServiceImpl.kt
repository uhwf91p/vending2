package com.example.order.service

import com.example.order.dto.CheckCodeRequest
import com.example.order.dto.SwitchCellRequest
import com.example.order.provider.CellProvider

class CellServiceImpl(
    private val cellProvider: CellProvider
) : CellService {

    override suspend fun openCellByCode(code: String, opened: Boolean) {
        cellProvider.checkCode(
            CheckCodeRequest(code = code)
        )?.let {
            cellProvider.switchCell(
                SwitchCellRequest(
                    cell = it.openCell,
                    opened = opened
                )
            )
        }
    }
}