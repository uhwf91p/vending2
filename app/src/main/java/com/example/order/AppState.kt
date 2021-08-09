package com.example.order

import com.example.order.Data.MainList

sealed class AppState {
    data class loadMainList(val mainList:List<MainList>): AppState()
}