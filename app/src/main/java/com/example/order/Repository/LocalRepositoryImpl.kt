package com.example.order.Repository

import com.example.order.Data.MainList
import com.example.order.Room.LocalDataBase.DatabaseFrom1CDAO
import com.example.order.Room.LocalDataBase.DatabaseFrom1CEntity
import com.example.order.Room.LocalDataBase.ResultEntity
import com.example.order.ViewModel.Converters

class LocalRepositoryImpl(private val localDataSource: DatabaseFrom1CDAO) : LocalRepository {
    private val converter: Converters = Converters()
    override fun putDataFromServer1CToLocalDatabase(mainListFromServer: List<MainList>) {
       for (mainList in mainListFromServer) {
           val data:DatabaseFrom1CEntity=converter.convertMainListToEntityDB1C(mainList.id1,mainList.id2,mainList.name,mainList.value)
            insertToDB(data)

          }

    }

    private fun insertToDB(data:DatabaseFrom1CEntity){
        localDataSource.insert(data)


    }

    override fun getAllDataDB1CEntity():List<MainList> {
        return converter.convertEntityDB1CToMainList(localDataSource.all1C())

    }

    override fun deleteAllData(){
        localDataSource.deleteall()
    }

    override fun getAllDataDBResultEntityToMainList(): List<MainList> {
        return converter.convertEntityResultToMainList(localDataSource.getAllUnfinishedResult())
    }

    override fun putDataToResultDB(resultMainList: List<MainList>) {
        val data=converter.convertRemListToResultEntity(resultMainList)
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

