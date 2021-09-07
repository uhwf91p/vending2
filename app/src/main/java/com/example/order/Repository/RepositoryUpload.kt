package com.example.order.Repository

import com.example.order.Data.MainList

interface RepositoryUpload {
    fun rememberMainList(mainList: MainList):MutableList<MainList>
    fun rememberValues (value:MainList):MutableList<MainList>

}
