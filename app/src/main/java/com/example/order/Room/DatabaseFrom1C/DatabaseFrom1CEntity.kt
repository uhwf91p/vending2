package com.example.order.Room.DatabaseFrom1C

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(primaryKeys = ["id1"])
data class DatabaseFrom1CEntity(

    val id1: String,
    val id2: String,
    var name:String,
    var value:String


)