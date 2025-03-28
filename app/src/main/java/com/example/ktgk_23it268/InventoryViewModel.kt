package com.example.ktgk_23it268

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class InventoryViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = InventoryDB.getDatabase(application).inventoryDao()
    val inventoryList: Flow<List<Inventory>> = dao.getAll()

    fun addItem(item: Inventory) = viewModelScope.launch {
        if (validateItem(item)) {
            dao.insert(item)
        }
    }

    fun updateItem(item: Inventory) = viewModelScope.launch {
        if (validateItem(item)) {
            dao.update(item)
        }
    }

    fun deleteItem(item: Inventory) = viewModelScope.launch {
        dao.delete(item)
    }

    private fun validateItem(item: Inventory): Boolean {
        return item.item_name.isNotBlank() && item.quantity > 0 && item.supplier.isNotBlank()
    }
}
