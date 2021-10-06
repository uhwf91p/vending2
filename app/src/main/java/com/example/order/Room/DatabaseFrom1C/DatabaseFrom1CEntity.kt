package com.example.order.Room.DatabaseFrom1C

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(primaryKeys = ["id1","id2","name"])
data class DatabaseFrom1CEntity(

    var id1: String,
    var id2: String,
    var name:String,
    var value:String


)