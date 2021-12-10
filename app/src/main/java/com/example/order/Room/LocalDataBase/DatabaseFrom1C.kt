package com.example.order.Room.LocalDataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DatabaseFrom1CEntity::class, ResultEntity::class],version=2,exportSchema=false)
abstract class DatabaseFrom1C :RoomDatabase() {
    abstract fun databaseFrom1CDAO(): DatabaseFrom1CDAO

}