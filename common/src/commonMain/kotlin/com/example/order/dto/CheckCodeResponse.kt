package com.example.order.dto

import kotlinx.serialization.Serializable

@Serializable
data class CheckCodeResponse(
    val openCell: Int,
    val message: String
)
