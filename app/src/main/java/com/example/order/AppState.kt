package com.example.order

import com.example.order.Data.MainList

sealed class AppState {
    data class Success(val mainList: List<MainList>): AppState()
    data class Error(val error:Throwable):AppState()
    data class Loading(val progress:Int?): AppState()

}