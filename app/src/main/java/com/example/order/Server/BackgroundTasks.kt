package com.example.order.Server

import android.os.Handler
import androidx.lifecycle.LiveData
import com.example.order.AppState
import com.example.order.Data.Keys
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.schedule


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