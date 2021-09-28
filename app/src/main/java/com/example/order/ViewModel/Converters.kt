package com.example.order.ViewModel

import androidx.lifecycle.ViewModel
import com.example.order.Data.MainList
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1CEntity
import com.example.order.Server.ServerResponseData

open class Converters : ViewModel() {
    fun converterFromResponseServerToMainList(serverResponse: ServerResponseData?): List<MainList> {
        val convertedList:List<MainList> = listOf()
        val x=0
        if (serverResponse != null) {
            for (serverDTO in serverResponse.listFromServer) {
                for(i in convertedList){

                    convertedList[x].name= serverDTO.name.toString()
                    convertedList[x].id1= serverDTO.id1.toString()
                    convertedList[x].id2= serverDTO.id2.toString()
                    convertedList[x].value= "0"
                    x+1




            }
        }

        }
        return convertedList

    }
    fun convertMainListToEntityDB1C(mainList: MainList):DatabaseFrom1CEntity{
      return DatabaseFrom1CEntity(mainList.id1,mainList.id2,mainList.name,"")
        }
    fun convertEntityDB1CToMainList(entityList:List<DatabaseFrom1CEntity>):List<MainList>{
        return entityList.map{MainList(it.id1,it.id2,it.name,it.value)

        }
    }


    }
