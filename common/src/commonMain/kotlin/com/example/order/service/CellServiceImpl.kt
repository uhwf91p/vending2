package com.example.order.service

import com.example.order.dto.CheckCodeRequest
import com.example.order.dto.SwitchCellRequest
import com.example.order.provider.CellProvider

class CellServiceImpl(
    private val cellProvider: CellProvider
) : CellService {

    override suspend fun openCellByCode(code: String, opened: Boolean) {
        val checkCodeResult = cellProvider.checkCode(
            CheckCodeRequest(code = code)
        )
        cellProvider.switchCell(
            SwitchCellRequest(
                cell = checkCodeResult.openCell,
                opened = opened
            )
        )
    }
}