package com.example.order.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.order.AppState
import com.example.order.Repository.Repository
import com.example.order.Repository.RepositoryImpl

class MainViewModel(private val repository: Repository = RepositoryImpl()) :
    ViewModel() {
    private val liveDataToObserve:MutableLiveData<AppState> = MutableLiveData()
    fun getData():LiveData<AppState>{
        return  liveDataToObserve
    }
    fun getMainListViewModel()=requestData()

    private fun requestData(){
        Thread{
            
            liveDataToObserve.postValue(AppState.loadMainList(repository.getMainList()))

        }.start()
    }
}