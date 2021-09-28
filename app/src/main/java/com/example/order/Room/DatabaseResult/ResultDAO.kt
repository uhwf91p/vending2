package com.example.order.Room.DatabaseResult

import android.database.Cursor
import androidx.room.*
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1CEntity

@Dao
interface ResultDAO {

      /*  @Query("SELECT*FROM DatabaseFrom1CEntity")
        fun all():List<ResultEntity>*/

        /*@Query("SELECT*FROM DatabaseFrom1CEntity WHERE name LIKE:name")
        fun getDataByWord(name:String):List<ResultEntity>
*/
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insert(entity: ResultEntity)

        @Update
        fun update(entity: ResultEntity)

        @Delete
        fun delete (entity: ResultEntity)

        @Query("DELETE FROM ResultEntity WHERE id1=:id1&id2=:id2")
        fun deleteById(id1:Int,id2:Int)

        @Query("SELECT id1, id2, name FROM ResultEntity")
        fun getHistoryCursor(): Cursor

        @Query("SELECT id1, id2, name FROM ResultEntity WHERE id1 = :id1&id2=:id2")
        fun getHistoryCursor(id1: Int,id2:Int): Cursor



    }
