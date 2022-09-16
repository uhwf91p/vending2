package com.example.order.app.domain.usecase

import com.example.order.core.GlobalConstAndVars

import com.example.order.app.domain.model.ListItem
import com.example.order.repository.LocalRepository
import com.example.order.repository.LocalRepositoryImpl
import com.example.order.core.App
import com.example.order.datasource.Room.DatabaseResult.ResultEntity
import java.math.RoundingMode
import java.text.DecimalFormat

class CreateListOfAllItemsFrom1CDBCaseImpl: CreateListOfAllItemsFrom1CDBCase {



    private val localRepository1C: LocalRepository = LocalRepositoryImpl(App.get1CDAO())
    private val hoursWorked=makeListOfWork(
       GlobalConstAndVars.NUMBERS_OF_VALUES_FOR_WORKED_HOURS,
       GlobalConstAndVars.STEP_FOR_WORKED_HOURS,"Отработано часов")
    private val converters:Converters= Converters()


    override suspend fun getListWithAllCells(): List<ListItem> {
        var startList: List<ListItem> = listOf()
        val dataFrom1C: List<ListItem>

        dataFrom1C = localRepository1C.getAllDataDB1CEntity()
        GlobalConstAndVars.listItemFromDb=dataFrom1C
        startList=dataFrom1C
        GlobalConstAndVars.GLOBAL_LIST=startList






        return startList
    }

    private fun createOrdersList(listItem: List<ListItem>): List<ListItem> {
        val startListItem: List<ListItem> = listItem.distinctBy { it.collection
        }
        val convertListItem:MutableList<ListItem> = mutableListOf()
        for (startList1 in startListItem) {
            convertListItem.add(swapValuesForOrdersListCreating(startList1.collection,startList1.documentFB,startList1.field,startList1.value))

        }
        return convertListItem
    }
      private fun makeListFromDB(key: String, listItem:List<ListItem>): List<ListItem> {
          val tempListItem: MutableList<ListItem> = mutableListOf()
          for (mainList in listItem) {
              if (mainList.collection == key) {
                  tempListItem.add(mainList)
              }
          }
          for (mainList in tempListItem) {
               mainList.value = mainList.field

                  }

          return tempListItem.distinctBy { it.field to it.collection to it.documentFB }
      }

    private fun makeStartList(listItem: List<ListItem>): List<ListItem> {
        val startListItem: List<ListItem> = listItem.distinctBy { it.collection }
        val convertListItem:MutableList<ListItem> = mutableListOf()
         for (startList1 in startListItem) {
           convertListItem.add(swapValuesForStartListCreating(startList1.collection,startList1.documentFB,startList1.field,startList1.value))

        }
        return convertListItem
    }

    private fun swapValuesForStartListCreating (id1:String, id2:String, name: String, value:String):ListItem{
        val objectForChange = ListItem(id1,id2,name,value,"","")

        objectForChange.field=objectForChange.collection
        objectForChange.documentFB=objectForChange.collection
        objectForChange.collection="0"
        return objectForChange
    }

    private fun swapValuesForOrdersListCreating(
        id1: String,
        id2: String,
        name: String,
        value: String,

    ): ListItem {

        return ListItem("0", id1, name, value,"","")
    }
    private fun makeListOfWork(numberOfValues:Int, step:Double, nameOfField:String):MutableList<ListItem>{
        val workListItem: MutableList<ListItem> = mutableListOf()
        var valueForWork=0.000
        for (i in 1..numberOfValues){
            valueForWork += step
            val roundedNumber = DecimalFormat("#.###")
            roundedNumber.roundingMode = RoundingMode.DOWN
                workListItem.add(
                    ListItem(
                        nameOfField,
                        roundedNumber.format(valueForWork).toString(),
                        roundedNumber.format(valueForWork).toString(),
                        GlobalConstAndVars.DEFAULT_VALUE_FOR_GENERATED_LIST,"",""
                    )
                )
            }

        return workListItem

    }

}










