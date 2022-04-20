package com.example.order.Repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.order.Data.GlobalConstAndVars
import com.example.order.Data.ItemOfList
import com.example.order.Room.DatabaseResult.ResultEntity
import com.example.order.app.App

class RepositoryMakeResultImpl: RepositoryMakeResult {
    private val localDataSource:LocalRepository=LocalRepositoryImpl(App.get1CDAO())

    @RequiresApi(Build.VERSION_CODES.N)
    override fun rememberListOfChosenItems(itemOfList: ItemOfList): MutableList<ItemOfList> {
        val rememberedItemOfList:MutableList<ItemOfList> = GlobalConstAndVars.listOfChosenItemOfLists
        val iterator=rememberedItemOfList.iterator()
        while (iterator.hasNext()) {
            val item=iterator.next()
            if (item.id1 == itemOfList.id1) {
                iterator.remove()
            }
        }
        rememberedItemOfList.add(itemOfList)
        GlobalConstAndVars.listOfChosenItemOfLists=rememberedItemOfList
        return GlobalConstAndVars.listOfChosenItemOfLists
    }
    override fun makeOrderFinished() {
        val listOfUnfinishedOrders = localDataSource.getAllDataDBResultEntity()
        for (orderFromDB in listOfUnfinishedOrders) {
            for (orderFromServer in GlobalConstAndVars.LIST_OF_FINISHED_ORDERS) {
                if (orderFromDB.uid == orderFromServer.id2) {
                    val tempResult= ResultEntity(orderFromDB.id1,orderFromDB.id2,orderFromDB.name,GlobalConstAndVars.MARKER_OF_FINISHED_ORDER,orderFromDB.uid)
                    localDataSource.insertToDBResult(tempResult)

                }



            }

        }


    }


}


