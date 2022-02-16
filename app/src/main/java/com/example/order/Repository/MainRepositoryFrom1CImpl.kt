package com.example.order.Repository

import com.example.order.Data.Keys

import com.example.order.Data.MainList
import com.example.order.ViewModel.Database1CViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

class MainRepositoryFrom1CImpl:MainRepisitoryFrom1C {
    private val repository: RepositoryGetMainList = RepositoryGetMainListImpl()

    private val dataBase1CViewModel: Database1CViewModel = Database1CViewModel()
    private val amountOfWorkList=makeListOfWork(Keys.NUMBERS_OF_VALUES_FOR_WORK_LIST,Keys.STEP_FOR_WORK_LIST,"Фактически отработано в натуре")
    private val hoursWorked=makeListOfWork(Keys.NUMBERS_OF_VALUES_FOR_WORKED_HOURS,Keys.STEP_FOR_WORKED_HOURS,"Отработано часов")
    // саделать маски для имен в главном списке
    override fun getListForChoice(): List<MainList> {
        val dataFrom1C: List<MainList>
        var testListBrigadir:MutableList<MainList> = mutableListOf()
        var testListTrakctorDriver:List<MainList> = listOf()

        if (Keys.LIST_KEY != "0") {
            dataFrom1C = Keys.LIST_FROM_DB

        }
        else {

            dataFrom1C = dataBase1CViewModel.getAllDataFromDB1C()
            Keys.LIST_FROM_DB=dataFrom1C

        }

        /*testListBrigadir=makeListFromDB("ФизическиеЛица",dataFrom1C)
              testListTrakctorDriver=makeListFromDB("ФизическиеЛица",dataFrom1C)*/

        val startList=makeStartList(dataFrom1C+amountOfWorkList+hoursWorked+
                testListBrigadir+testListTrakctorDriver)+dataFrom1C+amountOfWorkList+hoursWorked+ testListBrigadir+testListTrakctorDriver
        Keys.GLOBAL_LIST=startList

        return /*setDefaultValues(startList)*/startList
    }
    private fun makeListFromDB(key: String, list:List<MainList>): List<MainList> {
        val tempList: MutableList<MainList> = mutableListOf()
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

    private fun makeStartList(mainList: List<MainList>): List<MainList> {
        val startList: List<MainList> = mainList.distinctBy { it.id1 }
        val convertList:MutableList<MainList> = mutableListOf()
         for (startList1 in startList) {
           convertList.add(changeValues(startList1.id1,startList1.id2,startList1.name,startList1.value))

        }
        return convertList
    }

    fun changeValues (id1:String, id2:String, name: String, value:String):MainList{
        val objectForChange = MainList(id1,id2,name,value)

        objectForChange.name=objectForChange.id1
        objectForChange.id2=objectForChange.id1
        objectForChange.id1="0"
        return objectForChange
    }
    private fun makeListOfWork(numberOfValues:Int, step:Double, nameOfField:String):MutableList<MainList>{
        val workList: MutableList<MainList> = mutableListOf()
        var valueForWork=0.0
        for (i in 1..numberOfValues){
            valueForWork += step
            val roundedNumber = DecimalFormat("#.#")
            roundedNumber.roundingMode = RoundingMode.CEILING

            workList.add(MainList(nameOfField,roundedNumber.format(valueForWork).toString(),roundedNumber.format(valueForWork).toString(),"0"))
        }
        return workList

    }
    private fun setDefaultValues(startList: List<MainList>):List<MainList>{
        val listDefaultFromDB: List<MainList> = dataBase1CViewModel.getAllDataFromResultDB()
        for (mainList in startList) {
            for (listDefault in listDefaultFromDB){
                if (mainList.id1 == listDefault.id1) {
                    mainList.name=listDefault.name


                }

            }

        }
        return startList



    }

}










