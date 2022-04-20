package com.example.order.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.order.app.domain.AppState
import com.example.order.Data.GlobalConstAndVars
import com.example.order.app.domain.CreateListOfAllItemsFrom1CDB
import com.example.order.app.domain.CreateListOfAllItemsFrom1CDBImpl
import com.example.order.Server.Retrofit1C
import com.example.order.app.domain.Converters
import kotlinx.coroutines.*

class SaveViewModel(val liveDataToObserve:MutableLiveData<AppState> = MutableLiveData(),
                    private val retrofit1C: Retrofit1C = Retrofit1C(),
                    private val converters: Converters = Converters()
):ViewModel() {
    val mainRepository: CreateListOfAllItemsFrom1CDB = CreateListOfAllItemsFrom1CDBImpl()

    fun getDataFromServerForDB(): LiveData<AppState> {
        liveDataToObserve.value = AppState.Loading(null)
        return liveDataToObserve
    }
    fun awaiting()  {
        liveDataToObserve.value= AppState.Success(
            GlobalConstAndVars.DEFAULT_lIST
        )
        appCoroutineScope.launch {
            delay(3000)
        }

    }
    fun getGlobalLIst(){
        mainRepository.getListForChoice()
    }

    private val appCoroutineScope = CoroutineScope(
        Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            handleError(
                throwable
            )
        })

    private fun handleError(error: Throwable) {}





}