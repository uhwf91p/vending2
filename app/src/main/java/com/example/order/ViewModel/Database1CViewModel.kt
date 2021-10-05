package com.example.order.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.order.AppState
import com.example.order.Data.MainList
import com.example.order.Repository.LocalRepository1C
import com.example.order.Repository.LocalRepository1CImpl
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1CEntity
import com.example.order.Server.ServerResponseData
import com.example.order.app.App

class Database1CViewModel (
    private val resultLiveData:MutableLiveData<AppState> = MutableLiveData(),
    private val localRepository1C: LocalRepository1C = LocalRepository1CImpl(App.get1CDAO())
):ViewModel() {
    private val converters: Converters = Converters()

    fun getAllDataFromDB1C():List<MainList> {
        //resultLiveData.value = AppState.Loading
        return localRepository1C.getAllData()//resultLiveData.value = AppState.Success(localRepository1C.getAllData())


    }
}