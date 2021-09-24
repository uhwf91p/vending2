package com.example.order.Repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.order.Data.Keys
import com.example.order.Data.MainList

class RepositoryResultImpl: RepositoryResult {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun rememberMainList(mainList: MainList): MutableList<MainList> {
        var  rememberedMainList:MutableList<MainList> = Keys.MAIN_REMEMEBERED_LIST
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


}


