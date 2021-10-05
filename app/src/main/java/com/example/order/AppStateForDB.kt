package com.example.order

import com.example.order.Data.MainList
import com.example.order.Server.ServerResponseData

sealed class AppStateForDB {
    data class Success(val serverResponseData: List<MainList>): AppStateForDB()
    data class Error(val error:Throwable):AppStateForDB()
    data class Loading(val progress:Int?): AppStateForDB()

}