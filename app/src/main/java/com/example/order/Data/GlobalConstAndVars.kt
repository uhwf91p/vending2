package com.example.order.Data

import com.example.order.Server.ServerResponseData

object GlobalConstAndVars {
    const val DEFAULT_VALUE: String = "0"
    var listFromDb:List<ItemOfList> = mutableListOf()
    var LIST_KEY: String = DEFAULT_VALUE
    var count: Int = 0
    var KEY_FOR_INFLATE_MAIN_LIST = 0
    var listOfChosenItemOfLists: MutableList<ItemOfList> = mutableListOf()
    var DATABASE1C_NAME: String = "Database1C.db"
    const val STEP_FOR_WORK_LIST=0.01
    const val NUMBERS_OF_VALUES_FOR_WORK_LIST=3000
    const val STEP_FOR_WORKED_HOURS=1.0
    const val NUMBERS_OF_VALUES_FOR_WORKED_HOURS=50
    val DEFAULT_lIST= listOf(ItemOfList("","","",""))
    var DATE_OF_ORDER=""
    var GLOBAL_LIST= DEFAULT_lIST
    var listForFirstScreen:List<ItemOfList> = mutableListOf()
    val DEFAULD_VALUE_FOR_GENERATED_LIST=""
    var LIST_OF_FINISHED_ORDERS = listOf<ServerResponseData>()
    const val MARKER_OF_FINISHED_ORDER="1"
    var WORKED_OUT:String=""

}