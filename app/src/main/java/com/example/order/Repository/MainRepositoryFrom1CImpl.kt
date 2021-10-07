package com.example.order.Repository

import com.example.order.Data.Keys
import com.example.order.Data.MainList
import com.example.order.ViewModel.Database1CViewModel
import com.example.order.app.App

class MainRepositoryFrom1CImpl:MainRepisitoryFrom1C {
 private val dataBase1CviewModel:Database1CViewModel= Database1CViewModel()
    override fun getListForChoice(): List<MainList> {
        if (Keys.SWITCH == 0) {
            val dataFrom1C:List<MainList> = dataBase1CviewModel.getAllDataFromDB1C()
            val startList=makeStartList(dataFrom1C)

          return  dataFrom1C+startList

        }
        else
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
            MainList("1", "22", "Уходные работы на плодоносящих виноградниках Уходные работы на плодоносящих виноградниках Уходные работы на плодоносящих виноградниках Уходные работы на плодоносящих виноградниках Уходные работы на плодоносящих виноградниках", "0"),
            MainList("1", "23", "Уход за однолеткой", "0")


        )
    }
    private fun makeStartList(mainList:List<MainList>):List<MainList>{
        val startList:List<MainList> = mainList.distinctBy { it.id1 }
        for (list in startList) {
            list.id2=list.id1
            list.id1="0"

        }


    return  startList
    }



   /* val distinctList: List<Cat> = cats.distinctBy { it.name } // либо it.age
    println(distinctList.joinToString())*/

}