package com.example.order.Repository

import com.example.order.Data.MainList


class RepositoryImpl:Repository {
    override fun getMainList(): List<MainList> {
        return listOf(
            MainList(1,"Работа"),
            MainList(1,"Трактор"),
            MainList(1,"Тракторист"),
            MainList(1,"Участок"),
            MainList(1,"Объем в га"),
            MainList(1,"Человеко-часы"),
            MainList(1,"Дата"),
            MainList(1,"Вид работ"),


        )
    }

    override fun getDetails(): List<MainList> {
        return listOf(
            MainList(2,"Обрезка"),
            MainList(2,"Чизелевание"),
            MainList(2,"Посадка"),
            MainList(2,"Культивация"),
            MainList(2,"Чеканка")
        )
    }

    override fun getNumbers(): List<Int> {
        return listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)

    }

    override fun getDate(): List<Int> {
        return listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31)
    }


}