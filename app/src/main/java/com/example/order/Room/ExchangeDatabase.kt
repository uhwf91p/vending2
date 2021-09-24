package com.example.order.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ExchangeEntity::class],version=1,exportSchema=false)
abstract class ExchangeDatabase :RoomDatabase() {
    abstract fun exchangeDAO(): ExchangeDAO

}