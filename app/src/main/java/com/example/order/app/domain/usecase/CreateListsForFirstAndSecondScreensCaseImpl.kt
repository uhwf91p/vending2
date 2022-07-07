package com.example.order.app.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.order.core.GlobalConstAndVars
import com.example.order.app.domain.model.ListItem

class CreateListsForFirstAndSecondScreensCaseImpl: CreateListsForFirstAndSecondScreensCase {
    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun getTicketsList(key: String): List<ListItem> {

        return GlobalConstAndVars.GLOBAL_LIST.distinctBy { it.documentFB }


    }

    override fun getQuestionsAndAnswers(fieldsName: String, ticketNumber:String): List<ListItem> {
        var listForFilter=GlobalConstAndVars.GLOBAL_LIST
        listForFilter.filter { it.documentFB==ticketNumber }.filter { it.field.contains(fieldsName)  }
       return listForFilter


    }

}








