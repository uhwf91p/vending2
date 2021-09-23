package com.example.order.app

import android.app.Application
import androidx.room.Room
import com.example.order.Data.Keys
import com.example.order.Room.ExchangeDAO
import com.example.order.Room.ExchangeDatabase
import java.lang.IllegalStateException

class App: Application() {
    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        private var appInstance: App? = null
        private var db: ExchangeDatabase? = null
        private val DB_NAME = Keys.EXCHAGE_DATABASE_NAME
        fun getExchangeDAO(): ExchangeDAO {
            if (db == null) {
                synchronized(ExchangeDatabase::class.java){
                    if (db==null){
                        if (appInstance == null) {
                            throw IllegalStateException("Application ids null meanwhile creating database")

                        }
                        db= Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            ExchangeDatabase::class.java, DB_NAME)
                            .allowMainThreadQueries()
                            .build()


                    }
                }
            }
            return db!!.exchangeDAO()


            }


    }
}