package com.example.order.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.order.AppState
import com.example.order.Data.Keys
import com.example.order.Repository.RepositoryGetMainList
import com.example.order.Repository.RepositoryGetMainListImpl

class MainViewModel(
    private val repository: RepositoryGetMainList = RepositoryGetMainListImpl(),


) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> {
        liveDataToObserve.value = AppState.Loading(null)
        return liveDataToObserve
    }

    fun getMainListViewModel() = requestData()

    private fun requestData() {
        Thread {
            liveDataToObserve.postValue(AppState.Success(repository.getMainList(Keys.LIST_KEY)))
        }.start()
    }
}



