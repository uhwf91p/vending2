package com.example.order.Room.DataBaseFrom1C

import androidx.room.Entity


@Entity(primaryKeys = ["id1","id2","name"])
data class DatabaseFrom1CEntity(

    var id1: String,
    var id2: String,
    var name:String,
    var value:String


)