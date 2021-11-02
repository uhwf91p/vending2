package com.example.order.Repository

import com.example.order.Data.MainList

interface LocalRepository {
    fun putDataFromServer1CToLocalDatabase(mainListFromServer:List<MainList>)
    fun getAllDataDB1CEntity(): List<MainList>
    fun deleteAllData()
    fun getAllDataDBResultEntity():List<MainList>

}