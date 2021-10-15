package com.example.order.ViewModel

import androidx.lifecycle.ViewModel
import com.example.order.Data.MainList
import com.example.order.Repository.LocalRepository
import com.example.order.Repository.LocalRepositoryImpl
import com.example.order.app.App

class Database1CViewModel (

    private val localRepository1C: LocalRepository = LocalRepositoryImpl(App.get1CDAO())
):ViewModel() {
        fun getAllDataFromDB1C():List<MainList> {
        return localRepository1C.getAllData()


    }
}