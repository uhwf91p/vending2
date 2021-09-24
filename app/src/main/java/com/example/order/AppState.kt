package com.example.order

import com.example.order.Data.MainList

sealed class AppState {
    data class Success(val mainList: List<MainList>): AppState()
    class Error(val error:Throwable):AppState()
    object Loading:AppState()

}