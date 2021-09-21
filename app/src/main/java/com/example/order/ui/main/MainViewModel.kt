package com.example.order.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.order.AppState
import com.example.order.BuildConfig
import com.example.order.Data.Keys
import com.example.order.Repository.RepositoryGetMainList
import com.example.order.Repository.RepositoryGetMainListImpl
import com.example.order.Repository.Retrofit1C
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RepositoryGetMainList = RepositoryGetMainListImpl()) :
    ViewModel() {
    private val retrofit1C:Retrofit1C= Retrofit1C()
    private val liveDataToObserve:MutableLiveData<AppState> = MutableLiveData()
    fun getData():LiveData<AppState>{
        return  liveDataToObserve
    }
    fun getMainListViewModel()=requestData()

    private fun requestData(){
        Thread{
            
            liveDataToObserve.postValue(AppState.Success(repository.getMainList(Keys.LIST_KEY)))

        }.start()
    }
    fun getDataFromServer():LiveData<AppState>{
        viewModelScope.launch(Dispatchers.IO) {
            val res = retrofit1C.getRetrofit().getDataFrom1C(BuildConfig.APIKEY_FROM_1C)
            if (res.isSuccessful && res.body() != null)
                liveDataToObserve.postValue(
                    AppState.Success(/*res.body()!!*/repository.getMainList(Keys.LIST_KEY))///ЗАГЛУШКА!!!!ИСПРАВИТЬ НА КОНВЕРТЕР ИЗ ДЖЕЙСОНА В МЭЙНЛИСТ!!!!
                )
            else
                liveDataToObserve.value = AppState.Error(Throwable())
        }
        return liveDataToObserve
    }


    }


