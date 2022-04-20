package com.example.order.app.domain

import com.example.order.Data.GlobalConstAndVars

import com.example.order.Data.ItemOfList
import com.example.order.Repository.LocalRepository
import com.example.order.Repository.LocalRepositoryImpl
import com.example.order.Repository.RepositoryGetMainList
import com.example.order.Repository.RepositoryGetMainListImpl
import com.example.order.app.App
import java.math.RoundingMode
import java.text.DecimalFormat

class CreateListOfAllItemsFrom1CDBImpl: CreateListOfAllItemsFrom1CDB {
    private val repository: RepositoryGetMainList = RepositoryGetMainListImpl()

    /*private val dataBase1CViewModel: Database1CViewModel = Database1CViewModel()*/
    private val localRepository1C: LocalRepository = LocalRepositoryImpl(App.get1CDAO())
   /* private val amountOfWorkList=makeListOfWork(Keys.NUMBERS_OF_VALUES_FOR_WORK_LIST,Keys.STEP_FOR_WORK_LIST,"Фактически отработано в натуре")*/
    private val hoursWorked=makeListOfWork(GlobalConstAndVars.NUMBERS_OF_VALUES_FOR_WORKED_HOURS,GlobalConstAndVars.STEP_FOR_WORKED_HOURS,"Отработано часов")

    // саделать маски для имен в главном списке
    override fun getListForChoice(): List<ItemOfList> {
        val dataFrom1C: List<ItemOfList>

        //TODO() хардкод ниже - убрать

        val quality:MutableList<ItemOfList> = mutableListOf(
            ItemOfList("ДоплатаЗаКачество","Доплата за качество 0%","0",GlobalConstAndVars.DEFAULD_VALUE_FOR_GENERATED_LIST),
            ItemOfList("ДоплатаЗаКачество","Доплата за качество 20%","20",GlobalConstAndVars.DEFAULD_VALUE_FOR_GENERATED_LIST)
        )
        val difficult:MutableList<ItemOfList> = mutableListOf(
            ItemOfList("ДоплатаЗаТяжесть(Сложность)","ДоплатаЗаКачество0%","0",GlobalConstAndVars.DEFAULD_VALUE_FOR_GENERATED_LIST),
            ItemOfList("ДоплатаЗаТяжесть(Сложность)","ДоплатаЗаКачество12%","12",GlobalConstAndVars.DEFAULD_VALUE_FOR_GENERATED_LIST)
        )
        val refill:MutableList<ItemOfList> = mutableListOf(
            ItemOfList("ДоплатаЗаЗаправку","ДоплатаЗаЗаправку0%","0",GlobalConstAndVars.DEFAULD_VALUE_FOR_GENERATED_LIST),
            ItemOfList("ДоплатаЗаЗаправку","ДоплатаЗаЗаправку20%","20",GlobalConstAndVars.DEFAULD_VALUE_FOR_GENERATED_LIST)
        )
        val weekends:MutableList<ItemOfList> = mutableListOf(
            ItemOfList("ДоплатаЗаВыходные","ДоплатаЗаВыходные0%","0",GlobalConstAndVars.DEFAULD_VALUE_FOR_GENERATED_LIST),
            ItemOfList("ДоплатаЗаВыходные","ДоплатаЗаВыходные100%","100",GlobalConstAndVars.DEFAULD_VALUE_FOR_GENERATED_LIST)
        )

        if (GlobalConstAndVars.LIST_KEY != "0") {
            dataFrom1C = GlobalConstAndVars.listFromDb

        }
        else {


            dataFrom1C = localRepository1C.getAllDataDB1CEntity()
            GlobalConstAndVars.listFromDb=dataFrom1C

        }


        val startList=makeStartList(dataFrom1C/*+amountOfWorkList*/+hoursWorked+
                quality+difficult+refill+weekends)+dataFrom1C/*+amountOfWorkList*/+hoursWorked+ quality+difficult+refill+weekends
        GlobalConstAndVars.GLOBAL_LIST=startList

        return /*setDefaultValues(startList)*/startList
    }
    private fun makeListFromDB(key: String, list:List<ItemOfList>): List<ItemOfList> {
        val tempList: MutableList<ItemOfList> = mutableListOf()
        for (mainList in list) {
            if (mainList.id1 == key) {
                tempList.add(mainList)
            }
        }
        for (mainList in tempList) {
             mainList.value = mainList.name

                }





        return tempList.distinctBy { it.name to it.id1 to it.id2 }


    }

    private fun makeStartList(itemOfList: List<ItemOfList>): List<ItemOfList> {
        val startList: List<ItemOfList> = itemOfList.distinctBy { it.id1 }
        val convertList:MutableList<ItemOfList> = mutableListOf()
         for (startList1 in startList) {
           convertList.add(changeValues(startList1.id1,startList1.id2,startList1.name,startList1.value))

        }
        return convertList
    }

    fun changeValues (id1:String, id2:String, name: String, value:String):ItemOfList{
        val objectForChange = ItemOfList(id1,id2,name,value)

        objectForChange.name=objectForChange.id1
        objectForChange.id2=objectForChange.id1
        objectForChange.id1="0"
        return objectForChange
    }
    private fun makeListOfWork(numberOfValues:Int, step:Double, nameOfField:String):MutableList<ItemOfList>{
        val workList: MutableList<ItemOfList> = mutableListOf()
        var valueForWork=0.000
        for (i in 1..numberOfValues){
            valueForWork += step
            var roundedNumber = DecimalFormat("#.###")
            roundedNumber.roundingMode = RoundingMode.DOWN
                workList.add(
                    ItemOfList(
                        nameOfField,
                        roundedNumber.format(valueForWork).toString(),
                        roundedNumber.format(valueForWork).toString(),
                        GlobalConstAndVars.DEFAULD_VALUE_FOR_GENERATED_LIST
                    )
                )
            }




        return workList

    }
  /*  private fun setDefaultValues(startList: List<MainList>):List<MainList>{
        val listDefaultFromDB: List<MainList> = dataBase1CViewModel.getAllDataFromResultDB()
        for (mainList in startList) {
            for (listDefault in listDefaultFromDB){
                if (mainList.id1 == listDefault.id1) {
                    mainList.name=listDefault.name


                }

            }

        }
        return startList



    }*/

}










