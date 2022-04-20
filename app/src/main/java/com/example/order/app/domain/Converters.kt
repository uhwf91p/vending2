package com.example.order.app.domain

import androidx.lifecycle.ViewModel
import com.example.order.Data.ItemForSearchText
import com.example.order.Data.GlobalConstAndVars
import com.example.order.Data.ItemOfList
import com.example.order.Room.DataBaseFrom1C.DatabaseFrom1CEntity
import com.example.order.Room.DatabaseResult.ResultEntity
import com.example.order.Server.ServerResponseData
import java.util.*
import kotlin.collections.ArrayList

open class Converters : ViewModel() {
    fun converterFromResponseServerToMainList(serverResponse: List<ServerResponseData?>): List<ItemOfList> {
        val convertedList: MutableList<ItemOfList> = mutableListOf()

        for (list in serverResponse) {
            if (list != null) {
                convertedList.add(convertmakeMainListFromStrings(list.id1,list.id2,list.name))
            }

        }

        return convertedList

    }
    private fun convertmakeMainListFromStrings (id1:String?, id2:String?, name:String?):ItemOfList{
        return ItemOfList(id1!!,id2!!,name!!,GlobalConstAndVars.DEFAULD_VALUE_FOR_GENERATED_LIST)

    }
    fun convertMainListToEntityDB1C(id1:String, id2:String, name: String, value:String): DatabaseFrom1CEntity {
        val databaseFrom1CEntity= DatabaseFrom1CEntity("","","","")
        databaseFrom1CEntity.id1=id1
        databaseFrom1CEntity.id2=id2
        databaseFrom1CEntity.name=name
        databaseFrom1CEntity.value=value
        return databaseFrom1CEntity
    }
    fun convertEntityDB1CToMainList(entityList: List<DatabaseFrom1CEntity>): List<ItemOfList> {
        return entityList.map {
            ItemOfList(it.id1, it.id2, it.name, it.value)

        }



    }
    fun convertEntityResultToMainList(entityList: List<ResultEntity>):List<ItemOfList>{
        return entityList.map {  ItemOfList(it.id1, it.id2, it.name, it.uid) }
    }
    fun convertRemListToResultEntity(remList:List<ItemOfList>):List<ResultEntity>{
        val uid=UUID.randomUUID().toString()
        return remList.map { ResultEntity(it.id1,it.id2,it.name,it.value,uid

        ) }

    }
    fun convertMainlistToItemStorage(itemOfList:List<ItemOfList>):ArrayList<ItemForSearchText>{
        val arrListOfItemStorage= ArrayList<ItemForSearchText>()
        for (mainList in itemOfList) {
            arrListOfItemStorage.add(ItemForSearchText(mainList.id1,mainList.id2,mainList.name,mainList.value))


        }
        return arrListOfItemStorage


    }


    fun convertItemStorageToMainList(arrayList:ArrayList<ItemForSearchText>):List<ItemOfList>{
        val mainList= mutableListOf<ItemOfList>()
        for (item in arrayList) {
            mainList.add(ItemOfList(item.id1.toString(),item.id2.toString(),item.name.toString(),item.value.toString()))


        }
        return mainList

        }


}



