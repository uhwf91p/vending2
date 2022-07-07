package com.example.order.app.domain.usecase

import com.example.order.app.domain.model.ListItem


interface CreateListsForFirstAndSecondScreensCase {
   suspend fun getTicketsList(key: String):List<ListItem>
   fun getQuestionsAndAnswers(fieldsName: String, ticketNumber:String): List<ListItem>



}