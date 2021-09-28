package com.example.order.Repository

import com.example.order.Data.MainList

interface LocalRepository1C {
    fun putDataFromServer1CToLocalDatabase(mainListFromServer:List<MainList>)
    /*fun getAllData(): List<MainList>*/
}