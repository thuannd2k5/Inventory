package com.example.ktgk_23it268

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Inventory)  // Xóa kiểu trả về

    @Query("SELECT * FROM inventory ORDER BY id DESC")
    fun getAll(): Flow<List<Inventory>>

    @Update
    suspend fun update(item: Inventory)  // Xóa kiểu trả về

    @Delete
    suspend fun delete(item: Inventory)  // Xóa kiểu trả về
}
