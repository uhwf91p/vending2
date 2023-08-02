package com.example.order.service

import com.example.order.dto.CheckCodeRequest
import com.example.order.dto.SwitchCellRequest
import com.example.order.provider.CellProvider
import java.util.logging.Logger

class CellServiceImpl(
    private val cellProvider: CellProvider,
    private val serialService: SerialService
) : CellService {

    private val logger = Logger.getLogger(this::class.qualifiedName)

    override suspend fun openCellByCode(code: String) {
        logger.info("Sending qrCode = $code to get cell")
        cellProvider.checkCode(
            CheckCodeRequest(code = code)
        )?.let {
            if (it.openCell != null) {
                logger.info("Opening cell with number = ${it.openCell}")
                val result = serialService.writeString(it.openCell.toString())
                logger.info("Received bytes amount from cell with number = ${it.openCell}. Bytes amount = $result")
                val opened = result != - 1
                val switchCellRequest = SwitchCellRequest(
                    cell = it.openCell,
                    opened = opened
                )
                logger.info("Sending switch cell request. Request = $switchCellRequest")
                cellProvider.switchCell(switchCellRequest)
            } else {
                logger.warning("Received null open cell. Response = $it")
            }
        }
    }
}