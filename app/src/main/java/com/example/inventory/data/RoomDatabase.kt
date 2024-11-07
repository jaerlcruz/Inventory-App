package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    companion object { // allows access to the methods to create or get the database
        @Volatile
        private var Instance: InventoryDatabase? = null //keeps reference to the database when one has been created
        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                // use the database builder to get a database
                // passing in application context, database class, and a name for the database
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                .build() //creates the database instance
                .also { Instance = it } // keeps reference to recently created db instance
            }
        }
    }
}

