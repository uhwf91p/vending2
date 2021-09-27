package com.example.order.Room.DatabaseFrom1C

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1CEntity

@Database(entities = [DatabaseFrom1CEntity::class],version=1,exportSchema=false)
abstract class DatabaseFrom1C :RoomDatabase() {
    abstract fun databaseFrom1CDAO(): DatabaseFrom1CDAO

}