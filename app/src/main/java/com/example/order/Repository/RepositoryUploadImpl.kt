package com.example.order.Repository

import com.example.order.Data.MainList

class RepositoryUploadImpl: RepositoryUpload {
    override fun rememberMainList(mainList: MainList): MutableList<MainList> {
        /*val mainRepository:Repository=RepositoryImpl()*/
      /*  mainRepository.getMainList(Keys.KEY_FOR_INFLATE_MAIN_LIST)*/
        var  rememberedMainList:MutableList<MainList> = Keys.MAIN_REMEMEBERED_LIST
        rememberedMainList.add(mainList)
        Keys.MAIN_REMEMEBERED_LIST=rememberedMainList
        return Keys.MAIN_REMEMEBERED_LIST
    }

    override fun rememberValues(value: MainList): MutableList<MainList> {
        var rememberedDetailsList:MutableList<MainList> = mutableListOf()
        rememberedDetailsList.add(value)
        return rememberedDetailsList
    }

}


