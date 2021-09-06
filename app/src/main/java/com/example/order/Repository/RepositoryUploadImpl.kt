package com.example.order.Repository

import com.example.order.Data.MainList
import com.example.order.ui.main.MainFragment

class RepositoryUploadImpl: RepositoryUpload {
    override fun rememberList(mainList: MainList): MutableList<MainList> {
        val mainRepository:Repository=RepositoryImpl()
        mainRepository.getMainList(Keys.KEY_FOR_INFLATE_MAIN_LIST)
        var rememberedList:MutableList<MainList> = mutableListOf()
        rememberedList.add(mainList)
        return rememberedList
    }

}


