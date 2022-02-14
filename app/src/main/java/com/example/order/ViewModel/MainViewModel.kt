package com.example.order.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.order.AppState
import com.example.order.BuildConfig
import com.example.order.Data.Item
import com.example.order.Data.Keys
import com.example.order.Data.MainList
import com.example.order.Repository.RepositoryGetMainList
import com.example.order.Repository.RepositoryGetMainListImpl
import com.example.order.Server.Retrofit1C
import com.example.order.Server.ServerResponseData
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val repository: RepositoryGetMainList = RepositoryGetMainListImpl(),



) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val retrofit1C: Retrofit1C = Retrofit1C()
    private val converters: Converters = Converters()
    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Default+ SupervisorJob()+ CoroutineExceptionHandler { _, throwable -> handleError(throwable)  })

    private fun handleError(error: Throwable){}

    fun getData(): LiveData<AppState> {
        liveDataToObserve.value = AppState.Loading(null)
        return liveDataToObserve
    }

    fun getMainListViewModel() = requestData()

    private fun requestData() {
        viewModelCoroutineScope.launch {   liveDataToObserve.postValue(AppState.Success(repository.getMainList(Keys.LIST_KEY))) }



    }
    fun convertMainListToArrayListItem(mainList: List<MainList>): ArrayList<Item> {
        return converters.convertMainlistToItemStorage(mainList)

    }
    fun convertArrayListItemToMainList(itemList: ArrayList<Item>): List<MainList> {
        return converters.convertItemStorageToMainList(itemList)

    }

    fun pullDataToServer(resultList:List<MainList>)  {
        liveDataToObserve.value = AppState.Loading(null)
        val apiKey: String = BuildConfig.APIKEY_FROM_1C
        if (apiKey.isBlank()) {
            AppState.Error(Throwable("You need API key"))
        } else {
            retrofit1C.getRetrofit().pullDataTo1C(resultList).enqueue(object :
                Callback<List<ServerResponseData>> {
                override fun onResponse(
                    call: Call<List<ServerResponseData>>,
                    response: Response<List<ServerResponseData>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataToObserve.value=AppState.Success(resultList)


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



