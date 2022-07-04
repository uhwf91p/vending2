package com.example.order.app.domain.usecase

import androidx.lifecycle.ViewModel
import com.example.order.app.domain.model.SearchItem
import com.example.order.app.domain.model.ListItem
import com.example.order.app.domain.model.ServerResponseDataFireBase
import com.example.order.datasource.Room.DataBaseFrom1C.DatabaseFrom1CEntity
import com.example.order.datasource.Room.DatabaseResult.ResultEntity
import com.example.order.datasource.Server.ServerResponseData
import com.google.firebase.firestore.DocumentSnapshot
import java.util.*
import kotlin.collections.ArrayList

open class Converters : ViewModel() {
    fun converterFromResponseServerToMainList(serverResponse: List<ServerResponseData?>): List<ListItem> {
        val convertedListItem: MutableList<ListItem> = mutableListOf()

        for (list in serverResponse) {
            if (list != null) {
                convertedListItem.add(convertMainListFromStrings(list.id1,list.id2,list.name,list.value))
            }

        }

        return convertedListItem

    }
    private fun convertMainListFromStrings (id1:String?, id2:String?, name:String?,value:String?):ListItem{
        return ListItem(id1!!,id2!!,name!!, value!!)

    }
    fun convertMainListToEntityDB1C(id1:String, id2:String, name: String, value:String): DatabaseFrom1CEntity {
        val databaseFrom1CEntity= DatabaseFrom1CEntity("","","","")
        databaseFrom1CEntity.id1=id1
        databaseFrom1CEntity.dataType=id2
        databaseFrom1CEntity.ticketNumber=name
        databaseFrom1CEntity.value=value
        return databaseFrom1CEntity
    }
    fun convertEntityDB1CToMainList(entityList: List<DatabaseFrom1CEntity>): List<ListItem> {
        return entityList.map {
            ListItem(it.id1, it.dataType, it.ticketNumber, it.value)

        }



    }
    fun mapDocumentToRemoteTask(document: DocumentSnapshot) = document.toObject(
        ServerResponseDataFireBase::class.java)!!.apply { id = document.id }

    fun mapToDB(remoteTask: ServerResponseDataFireBase): DatabaseFrom1CEntity {


        return DatabaseFrom1CEntity(

            remoteTask.variant1,
            remoteTask.variant2,
            remoteTask.variant3,
            remoteTask.question,

            )
    }
    fun convertEntityResultToMainList(entityList: List<ResultEntity>):List<ListItem>{
        return entityList.map {  ListItem(it.id1, it.id2, it.name, it.uid) }
    }
    fun convertRemListToResultEntity(remListItem:List<ListItem>):List<ResultEntity>{
        val uid=UUID.randomUUID().toString()
        return remListItem.map { ResultEntity(it.id1,it.dataType,it.ticketNumber,it.value,uid

        ) }

    }
    fun convertListItemToItemStorage(listItem:List<ListItem>):ArrayList<SearchItem>{
        val arrListOfItemStorage= ArrayList<SearchItem>()
        for (mainList in listItem) {
            arrListOfItemStorage.add(SearchItem(mainList.id1,mainList.dataType,mainList.ticketNumber,mainList.value))


        }
        return arrListOfItemStorage


    }


    fun convertItemStorageToMainList(arrayList:ArrayList<SearchItem>):List<ListItem>{
        val mainList= mutableListOf<ListItem>()
        for (item in arrayList) {
            mainList.add(ListItem(item.id1.toString(),item.id2.toString(),item.name.toString(),item.value.toString()))


        }
        return mainList

        }
    fun convertEntityResultToMainListForOrederList(entityList: List<ResultEntity>):List<ListItem>{
        return entityList.map {  ListItem(it.uid, it.id1, it.name, it.value) }
    }


}



private fun swapValuesForOrdersListCreating(
    id1: String,
    id2: String,
    name: String,
    value: String,
    uid: String
): ListItem {

    return ListItem("0", uid, name, value)
}



