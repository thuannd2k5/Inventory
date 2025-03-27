package com.example.ktgk_23it268

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Inventory::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao
}
