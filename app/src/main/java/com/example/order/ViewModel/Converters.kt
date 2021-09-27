package com.example.order.ViewModel

import androidx.lifecycle.ViewModel
import com.example.order.Data.MainList
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1CEntity
import com.example.order.Server.ServerResponseData

open class Converters : ViewModel() {
    fun converterFromResponseServerToMainList(serverResponse: ServerResponseData?): List<MainList> {
        return listOf(MainList(serverResponse!!.id1, serverResponse.id2, serverResponse.name, ""))

    }
    fun convertMainListToEntityDB1C(mainList: MainList):DatabaseFrom1CEntity{
      return DatabaseFrom1CEntity(mainList.id1,mainList.id2,mainList.name,"")
        }
    fun convertEntityDB1CToMainList(entityList:List<DatabaseFrom1CEntity>):List<MainList>{
        return entityList.map{MainList(it.id1,it.id2,it.name,it.value)

        }
    }


    }
