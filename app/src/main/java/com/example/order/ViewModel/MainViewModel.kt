package com.example.order.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.order.AppState
import com.example.order.BuildConfig
import com.example.order.Data.Keys
import com.example.order.Repository.RepositoryGetMainList
import com.example.order.Repository.RepositoryGetMainListImpl
import com.example.order.Server.Retrofit1C
import com.example.order.Server.ServerResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RepositoryGetMainList = RepositoryGetMainListImpl()) :
    Converters() {
    private val retrofit1C: Retrofit1C = Retrofit1C()
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    fun getData(): LiveData<AppState> {
        return liveDataToObserve
    }

    fun getMainListViewModel() = requestData()

    private fun requestData() {
        Thread {

            liveDataToObserve.postValue(AppState.Success(repository.getMainList(Keys.LIST_KEY)))

        }.start()
    }

    fun getDataFromServer(): LiveData<AppState> {
        viewModelScope.launch(Dispatchers.IO) {
            val res = retrofit1C.getRetrofit().getDataFrom1C(BuildConfig.APIKEY_FROM_1C)
            val serverResponse: ServerResponseData? = res.body()
            if (res.isSuccessful && res.body() != null)
                liveDataToObserve.postValue(
                    AppState.Success(converterFromResponseServerToMainList(serverResponse))
                )
            else
                liveDataToObserve.value = AppState.Error(Throwable())
        }
        return liveDataToObserve
    }

}





