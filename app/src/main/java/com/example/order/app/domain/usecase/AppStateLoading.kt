package com.example.order.app.domain.usecase

import com.example.order.app.domain.model.ListItem

sealed class AppStateLoading {
    data class Success(val listItem: List<ListItem>): AppStateLoading()
    data class Error(val error:Throwable): AppStateLoading()
    data class Loading(val progress:Int?): AppStateLoading()

}