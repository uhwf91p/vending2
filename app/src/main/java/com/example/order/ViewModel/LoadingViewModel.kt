package com.example.order.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.order.AppState
import com.example.order.BuildConfig
import com.example.order.Server.Retrofit1C
import com.example.order.Server.ServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadingViewModel(val liveDataToObserve:MutableLiveData<AppState> = MutableLiveData(),
                       private val retrofit1C: Retrofit1C = Retrofit1C(),
                       private val converters: Converters = Converters()
):ViewModel() {

    fun getDataFromServerForDB(): LiveData<AppState> {

        return liveDataToObserve
    }

    fun getDataFromServer()  {
        liveDataToObserve.value = AppState.Loading(null)
        val apiKey: String = BuildConfig.APIKEY_FROM_1C
        if (apiKey.isBlank()) {
            AppState.Error(Throwable("You need API key"))
        } else {
            retrofit1C.getRetrofit().getDataFrom1C(apiKey/*,apiKey,apiKey,apiKey,apiKey,apiKey*/).enqueue(object :
                Callback<List<ServerResponseData>> {
                override fun onResponse(
                    call: Call<List<ServerResponseData>>,
                    response: Response<List<ServerResponseData>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataToObserve.value =
                            AppState.Success(
                                converters.converterFromResponseServerToMainList(
                                    response.body()!!
                                )
                            )
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataToObserve.value =
                                AppState.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataToObserve.value =
                                AppState.Error(Throwable(message))
                        }
                    }
                }
                override fun onFailure(call: Call<List<ServerResponseData>>, t: Throwable) {
                    liveDataToObserve.value = AppState.Error(t)
                }
            })
        }

    }

}