package com.example.order.Repository

import com.example.order.Data.Keys

import com.example.order.Data.MainList
import com.example.order.ViewModel.Database1CViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

class MainRepositoryFrom1CImpl:MainRepisitoryFrom1C {
    private val dataBase1CViewModel: Database1CViewModel = Database1CViewModel()
    private val amountOfWorkList=makeListOfWork(Keys.NUMBERS_OF_VALUES_FOR_WORK_LIST,Keys.STEP_FOR_WORK_LIST,"Фактически отработано в натуре")
    private val hoursWorked=makeListOfWork(Keys.NUMBERS_OF_VALUES_FOR_WORKED_HOURS,Keys.STEP_FOR_WORKED_HOURS,"Отработано часов")
    // саделать маски для имен в главном списке
    override fun getListForChoice(): List<MainList> {
           val dataFrom1C: List<MainList> = dataBase1CViewModel.getAllDataFromDB1C()
           return makeStartList(dataFrom1C+amountOfWorkList+hoursWorked)+dataFrom1C+amountOfWorkList+hoursWorked

    }

    private fun makeStartList(mainList: List<MainList>): List<MainList> {
        val startList: List<MainList> = mainList.distinctBy { it.id1 }
        val convertList:MutableList<MainList> = mutableListOf()
         for (startList1 in startList) {
           convertList.add(changeValues(startList1.id1,startList1.id2,startList1.name,startList1.value))

        }
        return convertList
    }

    private fun changeValues (id1:String, id2:String, name: String, value:String):MainList{
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

            workList.add(MainList(nameOfField,nameOfField+i,roundedNumber.format(valueForWork).toString(),"0"))
        }
        return workList

    }

}





/*
if (Keys.SWITCH == 0) {
    val dataFrom1C: List<MainList> = dataBase1CviewModel.getAllDataFromDB1C()

    return makeStartList(dataFrom1C+amountOfWorkList+hoursWorked)+dataFrom1C+amountOfWorkList+hoursWorked

} else
return listOf(
MainList("0", "1", "Вид работ", "0"),
MainList("0", "8", "Работа", "0"),
MainList("0", "2", "Трактор", "0"),
MainList("0", "3", "Тракторист", "0"),
MainList("0", "4", "Участок", "0"),
MainList("0", "5", "Объем в га", "0"),
MainList("0", "6", "Человеко-часы", "0"),
MainList("0", "7", "Дата", "0"),
MainList("8", "9", "Обрезка", "0"),
MainList("8", "10", "Чизелевание", "0"),
MainList("8", "12", "Культивация", "0"),
MainList("2", "13", "МТЗ 921", "0"),
MainList("2", "14", "Джон дир", "0"),
MainList("2", "15", "Фентд", "0"),
MainList("2", "16", "Валтра", "0"),
MainList("3", "17", "Иванов", "0"),
MainList("4", "18", "123", "0"),
MainList("5", "19", "123", "0"),
MainList("6", "20", "8", "0"),
MainList("7", "21", "1", "0"),
MainList("1","22","Уходные работы на плодоносящих виноградниках Уходные работы на плодоносящих виноградниках Уходные работы на плодоносящих виноградниках Уходные работы на плодоносящих виноградниках Уходные работы на плодоносящих виноградниках",                   "0"),
MainList("1", "23", "Уход за однолеткой", "0")
)
}
*/


