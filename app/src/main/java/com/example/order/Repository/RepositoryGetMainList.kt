package com.example.order.Repository

import com.example.order.Data.MainList


interface RepositoryGetMainList {
   fun getMainList(key: String):List<MainList>



}