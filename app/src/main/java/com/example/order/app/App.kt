package com.example.order.app

import android.app.Application
import androidx.room.Room
import com.example.order.Data.Keys
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1C
import com.example.order.Room.DatabaseFrom1C.DatabaseFrom1CDAO
import com.example.order.Room.DatabaseResult.ResultDAO
import com.example.order.Room.DatabaseResult.ResultDatabase
import java.lang.IllegalStateException

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var dbResult: ResultDatabase? = null
        private var db1C: DatabaseFrom1C? = null
        private val DB_RESULT_NAME_ = Keys.RESULT_DATABASE_NAME
        private val DB1C_NAME = Keys.DATABASE1C_NAME
        fun getResultDAO(): ResultDAO {
            if (dbResult == null) {
                synchronized(ResultDatabase::class.java){
                    if (dbResult==null){
                        if (appInstance == null) {
                            throw IllegalStateException("Application ids null meanwhile creating database")

                        }
                        dbResult= Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            ResultDatabase::class.java, DB_RESULT_NAME_)
                            .allowMainThreadQueries()
                            .build()


                    }
                }
            }
            return dbResult!!.ResultDatabaseDAO()


            }

        fun get1CDAO(): DatabaseFrom1CDAO {
            if (db1C == null) {
                synchronized(DatabaseFrom1C::class.java){
                    if (db1C==null){
                        if (appInstance == null) {
                            throw IllegalStateException("Application ids null meanwhile creating database")

                        }
                        db1C= Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            DatabaseFrom1C::class.java, DB1C_NAME)
                            .allowMainThreadQueries()
                            .build()


                    }
                }
            }
            return db1C!!.databaseFrom1CDAO()


        }



    }
}