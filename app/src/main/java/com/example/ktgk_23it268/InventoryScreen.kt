package com.example.ktgk_23it268

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun InventoryScreen(viewModel: InventoryViewModel = viewModel()) {
    val inventory by viewModel.inventoryList.collectAsStateWithLifecycle(initialValue = emptyList())
    var itemName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var supplier by remember { mutableStateOf("") }
    var selectedItem by remember { mutableStateOf<Inventory?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = "Inventory Management",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it.filter { char -> char.isDigit() } },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = supplier,
            onValueChange = { supplier = it },
            label = { Text("Supplier") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        var showDialog by remember { mutableStateOf(false) }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Warning") },
                text = { Text("Please fill in all fields before adding!") },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }

        Button(
            onClick = {
                if (itemName.isBlank() || quantity.isBlank() || supplier.isBlank()) {
                    showDialog = true
                } else {
                    if (selectedItem == null) {
                        viewModel.addItem(Inventory(item_name = itemName, quantity = quantity.toInt(), supplier = supplier))
                    } else {
                        viewModel.updateItem(selectedItem!!.copy(item_name = itemName, quantity = quantity.toInt(), supplier = supplier))
                        selectedItem = null
                    }
                    itemName = ""
                    quantity = ""
                    supplier = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
        ) {
            Text(if (selectedItem == null) "Add Item" else "Update Item", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            items(inventory) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp) // Tạo khoảng cách giữa các Card
                        .shadow(4.dp, shape = MaterialTheme.shapes.medium), // Hiệu ứng đổ bóng nhẹ
                    shape = MaterialTheme.shapes.medium, // Bo góc mềm mại
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "ID: ${item.id}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Name: ${item.item_name.ifBlank { "Unknown" }}",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Quantity: ${item.quantity}",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Supplier: ${item.supplier.ifBlank { "Unknown" }}",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween // Giãn cách 2 nút đều nhau
                        ) {
                            Button(
                                onClick = {
                                    itemName = item.item_name
                                    quantity = item.quantity.toString()
                                    supplier = item.supplier
                                    selectedItem = item
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_200)),
                                modifier = Modifier.weight(1f) // Chia đều không gian
                            ) {
                                Text("Update", color = Color.White)
                            }
                            Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa 2 nút
                            Button(
                                onClick = { viewModel.deleteItem(item) },
                                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red)),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Delete", color = Color.White)
                            }
                        }
                    }
                }
            }
        }

    }
}
