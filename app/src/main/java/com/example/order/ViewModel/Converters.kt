package com.example.order.ViewModel

import androidx.lifecycle.ViewModel
import com.example.order.Data.MainList
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1CEntity
import com.example.order.Server.ServerDTO
import com.example.order.Server.ServerResponseData

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
    fun convertmakeMainListFromStrings (id1:String?, id2:String?, name:String?):MainList{
        return MainList(id1!!,id2!!,name!!,"0")

    }

    fun convertMainListToEntityDB1C(mainList: MainList): DatabaseFrom1CEntity {
        return DatabaseFrom1CEntity(mainList.id1, mainList.id2, mainList.name, "")
    }

    fun convertEntityDB1CToMainList(entityList: List<DatabaseFrom1CEntity>): List<MainList> {
        return entityList.map {
            MainList(it.id1, it.id2, it.name, it.value)

        }

    }
}



