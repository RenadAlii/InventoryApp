package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// it's abstract because RoomDatabase create the imp for you.
// exportSchema = false so you don't keep schema version history backups.
@Database (entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase: RoomDatabase() {
    // so the DB know about the Dao.
    abstract fun itemDao(): ItemDao
    companion object{
       // because it will never be cached & all write & read will be done from main memory.
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        fun getDatabase(context: Context): ItemRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                )     .fallbackToDestructiveMigration()
                    .build()
                return instance
            }
        }

    }
}