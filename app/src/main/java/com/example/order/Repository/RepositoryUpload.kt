package com.example.order.Repository

import com.example.order.Data.MainList

interface RepositoryUpload {
    fun rememberList(mainList: MainList):MutableList<MainList>

}
