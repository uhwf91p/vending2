package com.example.order.Repository

import com.example.order.Data.MainList
import com.example.order.Room.LocalDataBase.ResultEntity

interface LocalRepository {
    fun putDataFromServer1CToLocalDatabase(mainListFromServer:List<MainList>)
    fun getAllDataDB1CEntity(): List<MainList>
    fun deleteAllData()
    fun getAllDataDBResultEntityToMainList():List<MainList>
    fun putDataToResultDB(resultMainList:List<MainList>)
    fun getAllDataDBResultEntity():List<ResultEntity>
    fun insertToDBResult(data: ResultEntity)


}