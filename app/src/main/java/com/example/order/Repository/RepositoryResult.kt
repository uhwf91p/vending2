package com.example.order.Repository

import com.example.order.Data.MainList

interface RepositoryResult {
    fun rememberMainList(mainList: MainList):MutableList<MainList>


}
