package com.example.order.provider

import com.example.order.config.AppConfig
import com.example.order.constant.ApiConstant
import com.example.order.dto.CheckCodeRequest
import com.example.order.dto.CheckCodeResponse
import com.example.order.dto.SwitchCellRequest
import com.example.order.httpClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.util.logging.Logger

class CellProviderImpl(
    private val appConfig: AppConfig
) : CellProvider {

    private val logger = Logger.getLogger(this::class.qualifiedName)

    // TODO: there are a problems in multiplatform projects when passing this dependency in constructor
    private val client: HttpClient = httpClient()

    override suspend fun checkCode(checkCodeRequest: CheckCodeRequest): CheckCodeResponse? {
        val response = client.post("${appConfig.domain}/${ApiConstant.CHECK_CODE_API}") {
            headers {
                val bearerToken = if (appConfig.accessToken.isNotEmpty()) {
                    "Bearer ${appConfig.accessToken}"
                } else {
                    "Bearer"
                }
                if (appConfig.accessToken.isNotEmpty()) {
                    append(HttpHeaders.Authorization, bearerToken)
                }
            }
            contentType(ContentType.Application.Json)
            setBody(checkCodeRequest)
        }
        return if (response.status.isSuccess()) {
            response.body<CheckCodeResponse>()
        } else {
            val body = response.bodyAsText()
            logger.warning("Received not success code from check_code: $response body = $body")
            null
        }
    }

    override suspend fun switchCell(switchCellRequest: SwitchCellRequest) {
        val response = client.post("${appConfig.domain}/${ApiConstant.SWITCH_CELL_API}") {
            headers {
                val bearerToken = if (appConfig.accessToken.isNotEmpty()) {
                    "Bearer ${appConfig.accessToken}"
                } else {
                    "Bearer"
                }
                append(HttpHeaders.Authorization, bearerToken)
            }
            contentType(ContentType.Application.Json)
            setBody(switchCellRequest)
        }
        if (!response.status.isSuccess()) {
            logger.warning("Received not success code from switch_cell: $response")
        }
    }
}