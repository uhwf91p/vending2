package com.example.order.Repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.order.Data.Keys
import com.example.order.Data.MainList
import com.example.order.Room.LocalDataBase.ResultEntity
import com.example.order.app.App

class RepositoryMakeResultImpl: RepositoryMakeResult {
    val localDataSource:LocalRepository=LocalRepositoryImpl(App.get1CDAO())

    @RequiresApi(Build.VERSION_CODES.N)
    override fun rememberMainList(mainList: MainList): MutableList<MainList> {
        val rememberedMainList:MutableList<MainList> = Keys.MAIN_REMEMEBERED_LIST
        val iterator=rememberedMainList.iterator()
        while (iterator.hasNext()) {
            val item=iterator.next()
            if (item.id1 == mainList.id1) {
                iterator.remove()
            }
        }
        rememberedMainList.add(mainList)
        Keys.MAIN_REMEMEBERED_LIST=rememberedMainList
        return Keys.MAIN_REMEMEBERED_LIST
    }
    override fun makeOrderFinished() {
        val listOfUnfinishedOrders = localDataSource.getAllDataDBResultEntity()
        for (orderFromDB in listOfUnfinishedOrders) {
            for (orderFromServer in Keys.LIST_OF_FINISHED_ORDERS) {
                if (orderFromDB.uid == orderFromServer.id2) {
                    val tempResult=ResultEntity(orderFromDB.id1,orderFromDB.id2,orderFromDB.name,Keys.MARKER_OF_FINISHED_ORDER,orderFromDB.uid)
                    localDataSource.insertToDBResult(tempResult)

                }



            }

        }


    }


}


