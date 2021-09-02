package com.example.order.Repository

import com.example.order.Data.MainList

class MainRepositoryImpl:MainRepisitory {
    override fun getListFrom1C(): List<MainList> {
        return listOf(
            MainList(0,1,"Работа"),
            MainList(0,2,"Трактор"),
            MainList(0,3,"Тракторист"),
            MainList(0,4,"Участок"),
            MainList(0,5,"Объем в га"),
            MainList(0,6,"Человеко-часы"),
            MainList(0,7,"Дата"),
            MainList(0,8,"Вид работ"),
            MainList(1,9,"Обрезка"),
            MainList(1,10,"Чизелевание"),
            MainList(1,11,"Посадка"),
            MainList(1,12,"Культивация"),
            MainList(2,13,"МТЗ 921"),
            MainList(2,14,"Джон дир"),
            MainList(2,15,"Фентд"),
            MainList(2,16,"Валтра")
        )
    }

}