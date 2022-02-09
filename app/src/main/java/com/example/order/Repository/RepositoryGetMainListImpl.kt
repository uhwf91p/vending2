package com.example.order.Repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.order.Data.Keys
import com.example.order.Data.MainList

class RepositoryGetMainListImpl:RepositoryGetMainList {
    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun getMainList(key: String): List<MainList> {
        //TODO() - убрать хардкод из выражения ниже
       /* val keyForTempList:String = if (key == "Бригадир"||key=="Тракторист") {
            "ФизическиеЛица"
        } else key*/
        val mainRepository:MainRepisitoryFrom1C=MainRepositoryFrom1CImpl()

    val rememberedList:MutableList<MainList> = Keys.MAIN_REMEMEBERED_LIST
      val listFrom1C=mainRepository.getListForChoice()
        val tempList:MutableList<MainList> = mutableListOf()
        for (mainList in listFrom1C) {
            if (mainList.id1 == key) {
               tempList.add(mainList)
                               }
        }
                for (mainList in tempList) {
                    for (remList in rememberedList) {
                        if (mainList.id2 == remList.id1) {
                            mainList.value=remList.name

                    }

                    }

                }

        return tempList.distinctBy { it.name to it.id1 to it.id2 }


                }

            }








