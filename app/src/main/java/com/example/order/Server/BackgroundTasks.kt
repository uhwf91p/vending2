package com.example.order.Server

import kotlinx.coroutines.*


class BackgroundTasks {


    val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Default+ SupervisorJob()+ CoroutineExceptionHandler { _, throwable -> handleError(throwable)  })

    private fun handleError(error: Throwable){}





    private suspend fun refreshListOfFinished() {
        delay(900000)
        viewModelCoroutineScope.launch {

        }




    }
}