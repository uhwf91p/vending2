package com.example.order.ViewModel

import androidx.lifecycle.ViewModel
import com.example.order.Data.MainList
import com.example.order.Server.ServerResponseData

open class Converters : ViewModel() {
    fun converterFromResponseServerToMainList(serverResponse: ServerResponseData?): List<MainList> {
        return listOf(MainList(serverResponse!!.id1, serverResponse.id2, serverResponse.name, ""))


    }
}