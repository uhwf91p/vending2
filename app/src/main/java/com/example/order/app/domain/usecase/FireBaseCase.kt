package com.example.order.app.domain.usecase

import com.example.order.app.domain.model.ServerResponseDataFireBase

interface FireBaseCase{
    fun executeGettingDataFromFirebase(collectionsName:String):MutableList<ServerResponseDataFireBase>
}