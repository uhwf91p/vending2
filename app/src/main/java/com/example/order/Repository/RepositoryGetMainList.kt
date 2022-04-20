package com.example.order.Repository

import com.example.order.Data.ItemOfList


interface RepositoryGetMainList {
   suspend fun getMainList(key: String):List<ItemOfList>



}