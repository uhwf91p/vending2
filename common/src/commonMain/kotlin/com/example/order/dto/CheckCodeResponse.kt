package com.example.order.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckCodeResponse(
    @SerialName("open_cell")
    val openCell: Int?,
    val message: String?
)
