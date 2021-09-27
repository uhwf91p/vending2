package com.example.order.app

import android.app.Application
import androidx.room.Room
import com.example.order.Data.Keys
import com.example.order.Room.ResultDAO
import com.example.order.Room.ResultDatabase
import java.lang.IllegalStateException

class App: Application() {
    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        private var appInstance: App? = null
        private var db: ResultDatabase? = null
        private val DB_NAME = Keys.EXCHAGE_DATABASE_NAME
        fun getExchangeDAO(): ResultDAO {
            if (db == null) {
                synchronized(ResultDatabase::class.java){
                    if (db==null){
                        if (appInstance == null) {
                            throw IllegalStateException("Application ids null meanwhile creating database")

                        }
                        db= Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            ResultDatabase::class.java, DB_NAME)
                            .allowMainThreadQueries()
                            .build()


                    }
                }
            }
            return db!!.exchangeDAO()


            }


    }
}