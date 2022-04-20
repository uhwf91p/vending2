package com.example.order.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.order.app.domain.AppState
import com.example.order.BuildConfig
import com.example.order.Data.ItemForSearchText
import com.example.order.Data.GlobalConstAndVars
import com.example.order.Data.ItemOfList
import com.example.order.Repository.*
import com.example.order.Server.Retrofit1C
import com.example.order.Server.ServerResponseData
import com.example.order.app.domain.Converters
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class MainViewModel(
    private val repository: RepositoryGetMainList = RepositoryGetMainListImpl(),
    private val makeResult: RepositoryMakeResult=RepositoryMakeResultImpl()



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
    fun checkCompleteness(referenceList:List<ItemOfList>, listForCheck:List<ItemOfList>, dateOfOrder:String, worked:String):String{
        var differents:MutableList<ItemOfList> = mutableListOf()

        for (refValue in referenceList) {
            var count=0
            for (checkedValue in listForCheck) {

                if (refValue.id2==checkedValue.id1) {
                    count += 1

                }


            }
            if (count<1){
                differents.add(refValue)
            }

        }
        return if (differents.isNotEmpty()||listForCheck.isEmpty()||dateOfOrder==""||worked==""){
            "Данные наряда заполнены не полностью"
        } else "Данные в наряде заполнены корректно"


    }


    private fun requestData() {
        viewModelCoroutineScope.launch {   liveDataToObserve.postValue(AppState.Success(repository.getMainList(GlobalConstAndVars.LIST_KEY))) }



    }
    fun convertMainListToArrayListItem(itemOfList: List<ItemOfList>): ArrayList<ItemForSearchText> {
        return converters.convertMainlistToItemStorage(itemOfList)

    }
    fun convertArrayListItemToMainList(itemList: ArrayList<ItemForSearchText>): List<ItemOfList> {
        return converters.convertItemStorageToMainList(itemList)

    }

    fun makeOrdersFinishedVM() {
        makeResult.makeOrderFinished()

    }
    fun rememberListOfChosenItemsVM(item:ItemOfList){
        makeResult.rememberListOfChosenItems(item)
    }

    fun getAllDataDBResultEntityToMainListVM(){

    }

    fun pullDataToServer(resultList:List<ItemOfList>)  {
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
                        liveDataToObserve.value= AppState.Success(resultList)
                        GlobalConstAndVars.LIST_OF_FINISHED_ORDERS = response.body()!!
                        makeOrdersFinishedVM()

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


companion object{
    fun newInstance()=MainViewModel()
}





}



