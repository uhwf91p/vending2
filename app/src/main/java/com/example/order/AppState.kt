package com.example.order

import com.example.order.Data.MainList
import com.example.order.Server.ServerResponseData

sealed class AppState {
    data class Success(val serverResponseData: List<ServerResponseData>): AppState()
    data class Error(val error:Throwable):AppState()
    data class Loading(val progress:Int?): AppState()

}