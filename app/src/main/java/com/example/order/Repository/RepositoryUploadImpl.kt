package com.example.order.Repository

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.order.Data.Keys
import com.example.order.Data.MainList

class RepositoryUploadImpl: RepositoryUpload {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun rememberMainList(mainList: MainList): MutableList<MainList> {
        /*val mainRepository:Repository=RepositoryImpl()*/
      /*  mainRepository.getMainList(Keys.KEY_FOR_INFLATE_MAIN_LIST)*/
        var  rememberedMainList:MutableList<MainList> = Keys.MAIN_REMEMEBERED_LIST
        for (remList in rememberedMainList) {
            if (remList.id1 == mainList.id1) {
                rememberedMainList.removeAt(rememberedMainList.indexOf(remList))
            }

        }
        rememberedMainList.add(mainList)
        Keys.MAIN_REMEMEBERED_LIST=rememberedMainList


        return Keys.MAIN_REMEMEBERED_LIST
    }


}


