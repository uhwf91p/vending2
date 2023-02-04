package com.example.order.datasource.Server

import kotlinx.coroutines.*


class BackgroundTasks {


    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Default+ SupervisorJob()+ CoroutineExceptionHandler { _, _ -> handleError() })

    private fun handleError() {}





    private suspend fun refreshListOfFinished() {
        delay(900000)
        viewModelCoroutineScope.launch {

        }




    }
}