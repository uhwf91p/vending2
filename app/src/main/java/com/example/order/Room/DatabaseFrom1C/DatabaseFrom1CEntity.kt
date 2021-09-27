package com.example.order.Room.DatabaseFrom1C

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DatabaseFrom1CEntity(
    @PrimaryKey(autoGenerate = true)
    val id1:Int,
    val id2:Int,
    /*var id3:Int,*/
    var name:String,
    var value:String


)