package com.example.order.dto

import kotlinx.serialization.Serializable

@Serializable
data class SwitchCellRequest(
    val cell: Int,
    val opened: Boolean
)
