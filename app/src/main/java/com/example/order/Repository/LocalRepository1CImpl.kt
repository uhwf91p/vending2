package com.example.order.Repository

import com.example.order.Data.MainList
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1CDAO
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1CEntity
import com.example.order.ViewModel.Converters

class LocalRepository1CImpl(private val localDataSource: DatabaseFrom1CDAO) : LocalRepository1C {
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

    override fun getAllData():List<MainList> {
        return converter.convertEntityDB1CToMainList(localDataSource.all())

    }

    override fun deleteAllData(){
        localDataSource.deleteall()
    }






}

