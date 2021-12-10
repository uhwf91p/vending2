package com.example.order.Data

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment

object Keys {
    const val DEFAULT_VALUE: String = "0"
    var START_LIST:List<MainList> = mutableListOf()
    var LIST_KEY: String = DEFAULT_VALUE
    var count: Int = 0
    var KEY_FOR_INFLATE_MAIN_LIST = 0
    var MAIN_REMEMEBERED_LIST: MutableList<MainList> = mutableListOf()

    var RESULT_DATABASE_NAME: String = "ExchangeDatabase.db"
    var DATABASE1C_NAME: String = "Database1C.db"
    const val SWITCH: Int =
        0//технический переключатель для выбора работы со статического списка или с базы данных
    const val STEP_FOR_WORK_LIST=0.1
    const val NUMBERS_OF_VALUES_FOR_WORK_LIST=6000
    const val STEP_FOR_WORKED_HOURS=1.0
    const val NUMBERS_OF_VALUES_FOR_WORKED_HOURS=6000
    val DEFAULT_FIELDS:List<String> = listOf("Бригадир")
    val DEFAULT_lIST= listOf(MainList("","","",""))
    fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, message , duration).show()
    }
}