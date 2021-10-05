package com.example.order.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.order.AppState
import com.example.order.AppStateForDB
import com.example.order.BuildConfig
import com.example.order.Data.Keys
import com.example.order.Data.MainList
import com.example.order.Repository.LocalRepository1C
import com.example.order.Repository.LocalRepository1CImpl
import com.example.order.Repository.RepositoryGetMainList
import com.example.order.Repository.RepositoryGetMainListImpl
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1CEntity
import com.example.order.Server.Retrofit1C
import com.example.order.Server.ServerResponseData
import com.example.order.app.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: RepositoryGetMainList = RepositoryGetMainListImpl(),

                    private val retrofit1C:Retrofit1C= Retrofit1C()

) :ViewModel() {
    private val converters:Converters= Converters()
    /*private val retrofit1C: Retrofit1C = Retrofit1C()*/
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val liveDataToObserveDB: MutableLiveData<AppStateForDB> = MutableLiveData()

    fun getData(): LiveData<AppState> {
        getDataFromServerToLocalDB()

        return liveDataToObserve
    }

    fun getMainListViewModel() = requestData()

    private fun requestData() {
        Thread {



            liveDataToObserveDB.postValue(AppStateForDB.Success(repository.getMainList(Keys.LIST_KEY)))

        }.start()
    }



    fun getDataFromServerToLocalDB() {
        liveDataToObserve.value = AppState.Loading(null)
        val apiKey: String = BuildConfig.APIKEY_FROM_1C
        if (apiKey.isBlank()) {
            AppState.Error(Throwable("You need API key"))
        } else {
            retrofit1C.getRetrofit().getDataFrom1C(apiKey).enqueue(object :
                Callback<List<ServerResponseData>> {
                override fun onResponse(
                    call: Call<List<ServerResponseData>>,
                    response: Response<List<ServerResponseData>>
                ) {
                    if (response.isSuccessful && response.body() != null) {

                        liveDataToObserve.value =
                            AppState.Success(response.body()!!)
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

/*
viewModelScope.launch(Dispatchers.IO) {
    val res = retrofit1C.getRetrofit().getDataFrom1C(BuildConfig.APIKEY_FROM_1C)
    val serverResponse: ServerResponseData? = res.body()
    val data:List<MainList> = converterFromResponseServerToMainList(serverResponse)
    if (res.isSuccessful && res.body() != null) {
        liveDataToObserve.postValue(AppState.Success(data))
        *//*localRepository1C.putDataFromServer1CToLocalDatabase(data)*//*
    }
    else {
        liveDataToObserve.value = AppState.Error(Throwable())
    }
}
return liveDataToObserve*/


