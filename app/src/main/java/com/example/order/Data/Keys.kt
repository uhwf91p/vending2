package com.example.order.Data

object Keys {
    const val DEFAULT_VALUE: Int = 0
    var LIST_KEY: Int = DEFAULT_VALUE
    var count:Int=0
    var KEY_FOR_INFLATE_MAIN_LIST=0
    var MAIN_REMEMEBERED_LIST: MutableList<MainList> = mutableListOf()
    var WORK_SELECTION:Int=0
    var RESULT_DATABASE_NAME:String="ExchangeDatabase.db"
    var DATABASE1C_NAME:String="Database1C.db"
    val SWITCH:Int=1//технический переключатель для выбора работы со статического списка или с базы данных



}