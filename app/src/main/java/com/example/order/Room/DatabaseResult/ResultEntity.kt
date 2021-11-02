package com.example.order.Room.DatabaseResult

import androidx.room.Entity



@Entity(primaryKeys = ["id1","id2","name"])
data class ResultEntity(

    var id1: String,
    var id2: String,
    var name:String,
    var value:String


)