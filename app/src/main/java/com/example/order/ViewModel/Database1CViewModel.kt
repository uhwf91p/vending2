package com.example.order.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.order.AppState
import com.example.order.Data.MainList
import com.example.order.Repository.LocalRepository1C
import com.example.order.Repository.LocalRepository1CImpl
import com.example.order.app.App

class Database1CViewModel (
    val resultLiveData:MutableLiveData<AppState> = MutableLiveData(),
    private val localRepository1C: LocalRepository1C = LocalRepository1CImpl(App.get1CDAO())
):ViewModel()
{

 fun getAllDataFromDB1C(){
      resultLiveData.value=AppState.Loading
      resultLiveData.value=AppState.Success(localRepository1C.getAllData())


  }
    fun saveDataToDB1C(listFromServer:List<MainList>){
        localRepository1C.putDataFromServer1CToLocalDatabase(listFromServer)
    }



}