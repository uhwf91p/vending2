package com.example.order.Repository

import com.example.order.Data.Keys

import com.example.order.Data.MainList
import com.example.order.ViewModel.Database1CViewModel

class MainRepositoryFrom1CImpl:MainRepisitoryFrom1C {
    private val dataBase1CviewModel: Database1CViewModel = Database1CViewModel()
    val yearList: List<MainList> = listOf(

        MainList("Год", "год1", "2021", "0"),
        MainList("Год", "год2", "2022", "0"),
        MainList("Год", "год3", "2023", "0"),
        MainList("Год", "год4", "2024", "0"),
        MainList("Год", "год5", "2025", "0"),
        MainList("Год", "год6", "2026", "0")

    )
    val dayList: List<MainList> = listOf(
        MainList("Число месяца", "дата1", "1", "0"),
        MainList("Число месяца", "дата2", "2", "0"),
        MainList("Число месяца", "дата3", "3", "0"),
        MainList("Число месяца", "дата4", "4", "0"),
        MainList("Число месяца", "дата5", "5", "0"),
        MainList("Число месяца", "дата6", "6", "0"),
        MainList("Число месяца", "дата7", "7", "0"),
        MainList("Число месяца", "дата8", "8", "0"),
        MainList("Число месяца", "дата9", "9", "0"),
        MainList("Число месяца", "дата10", "10", "0"),
        MainList("Число месяца", "дата11", "11", "0"),
        MainList("Число месяца", "дата12", "12", "0"),
        MainList("Число месяца", "дата13", "13", "0"),
        MainList("Число месяца", "дата14", "14", "0"),
        MainList("Число месяца", "дата15", "15", "0"),
        MainList("Число месяца", "дата16", "16", "0"),
        MainList("Число месяца", "дата17", "17", "0"),
        MainList("Число месяца", "дата18", "18", "0"),
        MainList("Число месяца", "дата19", "19", "0"),
        MainList("Число месяца", "дата20", "20", "0"),
        MainList("Число месяца", "дата21", "21", "0"),
        MainList("Число месяца", "дата22", "22", "0"),
        MainList("Число месяца", "дата23", "23", "0"),
        MainList("Число месяца", "дата24", "24", "0"),
        MainList("Число месяца", "дата25", "25", "0"),
        MainList("Число месяца", "дата26", "26", "0"),
        MainList("Число месяца", "дата27", "27", "0"),
        MainList("Число месяца", "дата28", "28", "0"),
        MainList("Число месяца", "дата29", "29", "0"),
        MainList("Число месяца", "дата30", "30", "0"),
        MainList("Число месяца", "дата31", "31", "0")

    )
    val monthList: List<MainList> = listOf(
        MainList("Месяц", "месяц1", "1", "0"),
        MainList("Месяц", "месяц2", "2", "0"),
        MainList("Месяц", "месяц3", "3", "0"),
        MainList("Месяц", "месяц4", "4", "0"),
        MainList("Месяц", "месяц5", "5", "0"),
        MainList("Месяц", "месяц6", "6", "0"),
        MainList("Месяц", "месяц7", "7", "0"),
        MainList("Месяц", "месяц8", "8", "0"),
        MainList("Месяц", "месяц9", "9", "0"),
        MainList("Месяц", "месяц10", "10", "0"),
        MainList("Месяц", "месяц11", "11", "0"),
        MainList("Месяц", "месяц12", "12", "0"),


        )

    override fun getListForChoice(): List<MainList> {

        if (Keys.SWITCH == 0) {
            val dataFrom1C: List<MainList> = dataBase1CviewModel.getAllDataFromDB1C()

           return makeStartList(dataFrom1C + dayList + monthList + yearList)+dataFrom1C + dayList + monthList + yearList

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
                MainList("1","22","Уходные работы на плодоносящих виноградниках Уходные работы на плодоносящих виноградниках Уходные работы на плодоносящих виноградниках Уходные работы на плодоносящих виноградниках Уходные работы на плодоносящих виноградниках",
                    "0"
                ),
                MainList("1", "23", "Уход за однолеткой", "0")
            )
    }

    private fun makeStartList(mainList: List<MainList>): List<MainList> {
        val startList: List<MainList> = mainList.distinctBy { it.id1 }
        val list5:MutableList<MainList> = mutableListOf()
         for (startList1 in startList) {
           list5.add(changeValues(startList1.id1,startList1.id2,startList1.name,startList1.value))

        }
        return list5
    }



    fun changeValues (id1:String, id2:String, name: String, value:String):MainList{
        val objectForChange:MainList= MainList(id1,id2,name,value)

        objectForChange.name=objectForChange.id1
        objectForChange.id2=objectForChange.id1
        objectForChange.id1="0"
        return objectForChange
    }

}