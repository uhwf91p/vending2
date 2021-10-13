package com.example.order.Repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.order.Data.Keys
import com.example.order.Data.MainList

class RepositoryGetMainListImpl:RepositoryGetMainList {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun getMainList(key: String): List<MainList> {
        val mainRepisitory:MainRepisitoryFrom1C=MainRepositoryFrom1CImpl()

    var rememberedList:MutableList<MainList> = Keys.MAIN_REMEMEBERED_LIST
      val listFrom1C=mainRepisitory.getListForChoice()
        var tempList:MutableList<MainList> = mutableListOf()
        for (mainList in listFrom1C) {
            if (mainList.id1 == key) {
               tempList.add(mainList)
                               }
        }
                for (mainList in tempList) {
                    for (remlist in rememberedList) {
                        if (mainList.id2 == remlist.id1) {
                            mainList.value=remlist.name

                    }

                    }

                }

        return tempList.distinctBy { it.name to it.id1 to it.id2 }


                }

            }








