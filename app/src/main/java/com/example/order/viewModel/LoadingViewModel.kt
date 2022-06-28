package com.example.order.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.order.BuildConfig
import com.example.order.app.domain.model.ListItem
import com.example.order.app.domain.usecase.*
import com.example.order.datasource.Server.Retrofit1C
import com.example.order.datasource.Server.ServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadingViewModel(val liveDataToObserve:MutableLiveData<AppState> = MutableLiveData(),
                       private val retrofit1C: Retrofit1C = Retrofit1C(),
                       private val converters: Converters = Converters()
):ViewModel() {
    private val createGlobalListCase: CreateListOfAllItemsFrom1CDBCase = CreateListOfAllItemsFrom1CDBCaseImpl()
    private val loadFrom1CtoDBCase:LoadDataFrom1CCase=LoadDataFrom1CCaseImpl()

    fun getDataFromServerForDB(): LiveData<AppState> {

        return liveDataToObserve
    }
    fun clearDB(){
        loadFrom1CtoDBCase.executeDeletingDataFromDb()
    }
    fun putDataFromServer1CToLocalDatabase(listFromServer:List<ListItem>){
        loadFrom1CtoDBCase.executeDownloadingDataFrom1CToDB(listFromServer)

    }

    fun getDataFromServer()  {
        liveDataToObserve.value = AppState.Loading(null)
        val apiKey: String = "BuildConfig.APIKEY_FROM_1C"
        if (apiKey.isBlank()) {
            AppState.Error(Throwable("You need API key"))
        } else {
            retrofit1C.getRetrofit().getDataFrom1C().enqueue(object :
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

    fun getGlobalLIst(){
        createGlobalListCase.getListForChoice()

    }


}