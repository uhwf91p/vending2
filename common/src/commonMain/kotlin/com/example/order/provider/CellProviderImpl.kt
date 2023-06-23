package com.example.order.provider

import com.example.order.config.AppConfig
import com.example.order.constant.ApiConstant
import com.example.order.dto.CheckCodeRequest
import com.example.order.dto.CheckCodeResponse
import com.example.order.dto.SwitchCellRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class CellProviderImpl(
    private val client: HttpClient = HttpClient()
) : CellProvider {

    override suspend fun checkCode(checkCodeRequest: CheckCodeRequest): CheckCodeResponse = client.post("${AppConfig.domain}/${ApiConstant.CHECK_CODE_API}") {
        headers {
            append(HttpHeaders.Authorization, AppConfig.accessToken)
        }
        contentType(ContentType.Application.Json)
        setBody(checkCodeRequest)
    }.body()

    override suspend fun switchCell(switchCellRequest: SwitchCellRequest) {
        client.post("${AppConfig.domain}/${ApiConstant.SWITCH_CELL_API}") {
            headers {
                append(HttpHeaders.Authorization, AppConfig.accessToken)
            }
            contentType(ContentType.Application.Json)
            setBody(switchCellRequest)
        }
    }
}