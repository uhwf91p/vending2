package com.example.order.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

const val ID1 = "id1"
const val ID2 = "Iid2"
const val NAME = "name"
const val VALUE="value"
@Entity
data class ExchageEntity(
    @PrimaryKey(autoGenerate = true)
    val id1:Int,
    val id2:Int,
    /*var id3:Int,*/
    var name:String,
    var value:String


)