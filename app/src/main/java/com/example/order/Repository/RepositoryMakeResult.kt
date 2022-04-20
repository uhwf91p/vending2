package com.example.order.Repository

import com.example.order.Data.ItemOfList

interface RepositoryMakeResult {
    fun rememberListOfChosenItems(itemOfList: ItemOfList):MutableList<ItemOfList>
    fun makeOrderFinished()



}
