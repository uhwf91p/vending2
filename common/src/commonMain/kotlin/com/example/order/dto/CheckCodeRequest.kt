package com.example.order.dto

import kotlinx.serialization.Serializable

@Serializable
data class CheckCodeRequest(
    val code: String
)
