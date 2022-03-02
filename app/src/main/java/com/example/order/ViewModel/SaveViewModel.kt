package com.example.order.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.order.AppState
import com.example.order.BuildConfig
import com.example.order.Data.Keys
import com.example.order.Data.MainList
import com.example.order.Repository.MainRepisitoryFrom1C
import com.example.order.Repository.MainRepositoryFrom1CImpl
import com.example.order.Server.Retrofit1C
import com.example.order.Server.ServerResponseData
import com.example.order.app.App
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SaveViewModel(val liveDataToObserve:MutableLiveData<AppState> = MutableLiveData(),
                    private val retrofit1C: Retrofit1C = Retrofit1C(),
                    private val converters: Converters = Converters()
):ViewModel() {
    val mainRepository: MainRepisitoryFrom1C = MainRepositoryFrom1CImpl()

    fun getDataFromServerForDB(): LiveData<AppState> {
        liveDataToObserve.value = AppState.Loading(null)
        return liveDataToObserve
    }
    fun awaiting()  {
        liveDataToObserve.value= AppState.Success(
            Keys.DEFAULT_lIST
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