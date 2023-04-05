package com.example.order.ui

import kotlinx.coroutines.flow.StateFlow

expect class MainViewModel{
   val uiState: StateFlow<MainState>

    fun onConnect()

    fun onInputChange(value: String)

    fun onOpen()
}