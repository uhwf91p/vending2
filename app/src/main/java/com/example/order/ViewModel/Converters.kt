package com.example.order.ViewModel

import androidx.lifecycle.ViewModel
import com.example.order.Data.MainList
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1CEntity
import com.example.order.Room.DatabaseResult.ResultEntity
import com.example.order.Server.ServerResponseData
import java.util.*

open class Converters : ViewModel() {
    fun converterFromResponseServerToMainList(serverResponse: List<ServerResponseData?>): List<MainList> {
        val convertedList: MutableList<MainList> = mutableListOf()

        for (list in serverResponse) {
            if (list != null) {
                convertedList.add(convertmakeMainListFromStrings(list.id1,list.id2,list.name))
            }

        }

        return convertedList

    }
    private fun convertmakeMainListFromStrings (id1:String?, id2:String?, name:String?):MainList{
        return MainList(id1!!,id2!!,name!!,"0")

    }
    fun convertMainListToEntityDB1C(id1:String, id2:String, name: String, value:String): DatabaseFrom1CEntity {
        val databaseFrom1CEntity= DatabaseFrom1CEntity("","","","")
        databaseFrom1CEntity.id1=id1
        databaseFrom1CEntity.id2=id2
        databaseFrom1CEntity.name=name
        databaseFrom1CEntity.value=value
        return databaseFrom1CEntity
    }
    fun convertEntityDB1CToMainList(entityList: List<DatabaseFrom1CEntity>): List<MainList> {
        return entityList.map {
            MainList(it.id1, it.id2, it.name, it.value)

        }



    }
    fun convertEntityResultToMainList(entityList: List<ResultEntity>):List<MainList>{
        return entityList.map {  MainList(it.id1, it.id2, it.name, it.value) }
    }
    fun convertRemListToResultEntity(remList:List<MainList>):List<ResultEntity>{
        return remList.map { ResultEntity(it.id1,it.id2,it.name,it.value,
            UUID.randomUUID().toString()
        ) }

    }


}



