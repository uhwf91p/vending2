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

}