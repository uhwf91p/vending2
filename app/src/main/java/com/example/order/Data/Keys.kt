package com.example.order.Data

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment

object Keys {
    const val DEFAULT_VALUE: String = "0"
    var LIST_FROM_DB:List<MainList> = mutableListOf()
    var LIST_KEY: String = DEFAULT_VALUE
    var count: Int = 0
    var KEY_FOR_INFLATE_MAIN_LIST = 0
    var MAIN_REMEMEBERED_LIST: MutableList<MainList> = mutableListOf()
    var RESULT_DATABASE_NAME: String = "ExchangeDatabase.db"
    var DATABASE1C_NAME: String = "Database1C.db"
    const val SWITCH: Int =
        0//технический переключатель для выбора работы со статического списка или с базы данных
    const val STEP_FOR_WORK_LIST=0.1
    const val NUMBERS_OF_VALUES_FOR_WORK_LIST=100
    const val STEP_FOR_WORKED_HOURS=1.0
    const val NUMBERS_OF_VALUES_FOR_WORKED_HOURS=50
    val DEFAULT_lIST= listOf(MainList("","","",""))
    val DEFAULT_MAINlIST=MainList("","","","")
    var DATE_OF_ORDER=""
    var GLOBAL_LIST= DEFAULT_lIST
    var LIST_FOR_FIRST_SCREEN:List<MainList> = mutableListOf()

}