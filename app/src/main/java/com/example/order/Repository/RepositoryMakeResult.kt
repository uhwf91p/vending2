package com.example.order.Repository

import com.example.order.Data.MainList

interface RepositoryMakeResult {
    fun rememberMainList(mainList: MainList):MutableList<MainList>



}
