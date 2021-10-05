package com.example.order.ViewModel

import androidx.lifecycle.ViewModel
import com.example.order.Data.MainList
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1CEntity
import com.example.order.Server.ServerDTO
import com.example.order.Server.ServerResponseData

open class Converters : ViewModel() {
    fun converterFromResponseServerToMainList(serverResponse: List<ServerResponseData?>): List<MainList> {
        val convertedList: MutableList<MainList> = mutableListOf()
        val mainList = MainList("", "", "", "")


            val f = serverResponse.size
            for (k in 1..f) {
                convertedList.add(mainList)
            }





        convertedList[10].id2= serverResponse[10]?.id2.toString()
        convertedList[20].id2= serverResponse[20]?.id2.toString()








        return convertedList







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



