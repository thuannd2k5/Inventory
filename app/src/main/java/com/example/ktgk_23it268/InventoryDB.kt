package com.example.ktgk_23it268

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Inventory::class], version = 1, exportSchema = false)
abstract class InventoryDB : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao

    companion object {
        @Volatile
        private var INSTANCE: InventoryDB? = null

        fun getDatabase(context: Context): InventoryDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InventoryDB::class.java,
                    "InventoryDB"
                ).fallbackToDestructiveMigration() // Reset DB nếu thay đổi cấu trúc
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
