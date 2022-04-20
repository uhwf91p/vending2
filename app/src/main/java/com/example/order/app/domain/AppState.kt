package com.example.order.app.domain

import com.example.order.Data.ItemOfList

sealed class AppState {
    data class Success(val itemOfList: List<ItemOfList>): AppState()
    data class Error(val error:Throwable): AppState()
    data class Loading(val progress:Int?): AppState()

}