package com.example.order.app

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.order.Data.Keys
import com.example.order.Room.LocalDataBase.DatabaseFrom1C
import com.example.order.Room.LocalDataBase.DatabaseFrom1CDAO
import com.example.order.Room.DatabaseResult.ResultDatabase
import com.example.order.ViewModel.LoadingViewModel
import kotlinx.coroutines.*
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

        private val DB1C_NAME = Keys.DATABASE1C_NAME



        fun get1CDAO(): DatabaseFrom1CDAO {
            if (db1C == null) {
                synchronized(DatabaseFrom1C::class.java) {
                    if (db1C == null) {
                        if (appInstance == null) {
                            throw IllegalStateException("Application ids null meanwhile creating database")

                        }
                        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
                            override fun migrate(database: SupportSQLiteDatabase) {
                                database.execSQL("CREATE TABLE `ResultEntity` ( `id1` TEXT NOT NULL,`id2` TEXT NOT NULL,`name` TEXT NOT NULL,`value` TEXT NOT NULL,PRIMARY KEY   (`id1`,`id2`,`name`) ) ")


                            }
                        }

                        db1C = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            DatabaseFrom1C::class.java, DB1C_NAME
                        )
                            .allowMainThreadQueries()
                            /*.addMigrations(MIGRATION_1_2)*/
                            .build()


                    }
                }
            }
            return db1C!!.databaseFrom1CDAO()


        }





    }
}/*  public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
        database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                + "`name` TEXT, PRIMARY KEY(`id`))");
                ,PRIMARY KEY("id1","id2","name"
     TableInfo{name='ResultEntity', columns={name=Column{name='name', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=3, defaultValue='null'}, value=Column{name='value', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, id2=Column{name='id2', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=2, defaultValue='null'}, id1=Column{name='id1', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=1, defaultValue='null'}}, foreignKeys=[], indices=[]}
     Found:
    TableInfo{name='ResultEntity', columns={id2=Column{name='id2', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}, id1=Column{name='id1', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}, name=Column{name='name', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}, value=Column{name='value', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}}, foreignKeys=[], indices=[]}

{name='name', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=3, defaultValue='null'}
{name='name', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}
    }
};

Room.databaseBuilder(getApplicationContext(), MyDb.class, "database-name")
    .addMigrations(MIGRATION_1_2).build();*/