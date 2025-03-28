package com.example.ktgk_23it268

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory")
data class Inventory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val item_name: String,
    val quantity: Int,
    val supplier: String
)
