package com.example.order.Repository

import com.example.order.Data.ItemOfList
import com.example.order.Room.DataBaseFrom1C.DatabaseFrom1CDAO
import com.example.order.Room.DataBaseFrom1C.DatabaseFrom1CEntity
import com.example.order.Room.DatabaseResult.ResultEntity
import com.example.order.app.domain.Converters

class LocalRepositoryImpl(private val localDataSource: DatabaseFrom1CDAO) : LocalRepository {
    private val converter: Converters = Converters()
    override fun putDataFromServer1CToLocalDatabase(itemOfListFromServer: List<ItemOfList>) {
       for (mainList in itemOfListFromServer) {
           val data:DatabaseFrom1CEntity=converter.convertMainListToEntityDB1C(mainList.id1,mainList.id2,mainList.name,mainList.value)
            insertToDB(data)

          }

    }

    private fun insertToDB(data:DatabaseFrom1CEntity){
        localDataSource.insert(data)


    }

    override fun getAllDataDB1CEntity():List<ItemOfList> {
        return converter.convertEntityDB1CToMainList(localDataSource.all1C())

    }

    override fun deleteAllData(){
        localDataSource.deleteall()
    }

    override fun getAllDataDBResultEntityToMainList(): List<ItemOfList> {
        return converter.convertEntityResultToMainList(localDataSource.getAllUnfinishedResult())
    }

    override fun putDataToResultDB(resultItemOfList: List<ItemOfList>) {
        val data=converter.convertRemListToResultEntity(resultItemOfList)
        for (mainList in data) {

            insertToDBResult(mainList)

        }
    }

    override fun getAllDataDBResultEntity(): List<ResultEntity> {
        return localDataSource.getAllUnfinishedResult()
    }

    override fun insertToDBResult(data: ResultEntity){
        localDataSource.insertDataToResult(data)


    }





}

