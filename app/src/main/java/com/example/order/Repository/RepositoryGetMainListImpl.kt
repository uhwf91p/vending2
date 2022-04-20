package com.example.order.Repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.order.Data.GlobalConstAndVars
import com.example.order.Data.ItemOfList

class RepositoryGetMainListImpl:RepositoryGetMainList {
    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun getMainList(key: String): List<ItemOfList> {

    val rememberedList:MutableList<ItemOfList> = GlobalConstAndVars.listOfChosenItemOfLists
        val listFrom1C=GlobalConstAndVars.GLOBAL_LIST
        val tempList:MutableList<ItemOfList> = mutableListOf()
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
        GlobalConstAndVars.listForFirstScreen=tempList.distinctBy { it.name to it.id1 to it.id2 }
        return  GlobalConstAndVars.listForFirstScreen


                }

            }








