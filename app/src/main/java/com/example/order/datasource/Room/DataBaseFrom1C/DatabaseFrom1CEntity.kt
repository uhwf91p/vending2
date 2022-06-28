package com.example.order.datasource.Room.DataBaseFrom1C

import androidx.room.Entity


@Entity(primaryKeys = ["id1","dataType","ticketNumber"])
data class DatabaseFrom1CEntity(

    var id1: String,
    var dataType: String,
    var ticketNumber:String,
    var value:String


)