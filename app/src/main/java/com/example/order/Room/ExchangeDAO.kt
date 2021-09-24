package com.example.order.Room

import android.database.Cursor
import androidx.room.*
@Dao
interface ExchangeDAO {

        @Query("SELECT*FROM ExchangeEntity")
        fun all():List<ExchangeEntity>

        @Query("SELECT*FROM ExchangeEntity WHERE name LIKE:name")
        fun getDataByWord(name:String):List<ExchangeEntity>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insert(entity:ExchangeEntity)

        @Update
        fun update(entity: ExchangeEntity)

        @Delete
        fun delete (entity: ExchangeEntity)

        @Query("DELETE FROM ExchangeEntity WHERE id1=:id1&id2=:id2")
        fun deleteById(id1:Int,id2:Int)

        @Query("SELECT id1, id2, name FROM ExchangeEntity")
        fun getHistoryCursor(): Cursor

        @Query("SELECT id1, id2, name FROM ExchangeEntity WHERE id1 = :id1&id2=:id2")
        fun getHistoryCursor(id1: Int,id2:Int): Cursor



    }
