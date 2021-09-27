package com.example.order.Room.DatabaseResult

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1CEntity

@Database(entities = [ResultEntity::class],version=1,exportSchema=false)
abstract class ResultDatabase :RoomDatabase() {
    abstract fun ResultDatabaseDAO(): ResultDAO

}