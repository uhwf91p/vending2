package com.example.order.Repository

import com.example.order.Data.ItemOfList
import com.example.order.Room.DatabaseResult.ResultEntity

interface LocalRepository {
    fun putDataFromServer1CToLocalDatabase(itemOfListFromServer:List<ItemOfList>)
    fun getAllDataDB1CEntity(): List<ItemOfList>
    fun deleteAllData()
    fun getAllDataDBResultEntityToMainList():List<ItemOfList>
    fun putDataToResultDB(resultItemOfList:List<ItemOfList>)
    fun getAllDataDBResultEntity():List<ResultEntity>
    fun insertToDBResult(data: ResultEntity)


}