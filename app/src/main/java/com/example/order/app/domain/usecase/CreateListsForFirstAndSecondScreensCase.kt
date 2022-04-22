package com.example.order.app.domain.usecase

import com.example.order.app.domain.model.ListItem


interface CreateListsForFirstAndSecondScreensCase {
   suspend fun getMainList(key: String):List<ListItem>



}