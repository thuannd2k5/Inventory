package com.example.ktgk_23it268

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inventoryViewModel = ViewModelProvider(this).get(InventoryViewModel::class.java)
        val photoViewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)

        enableEdgeToEdge()
        setContent {
            MainScreen(inventoryViewModel, photoViewModel)
        }
    }
}

@Composable
fun MainScreen(inventoryViewModel: InventoryViewModel, photoViewModel: PhotoViewModel) {
    var selectedScreen by remember { mutableStateOf("inventory") }

    Scaffold(
        bottomBar = {
            BottomAppBar {
                NavigationBarItem(
                    selected = selectedScreen == "inventory",
                    onClick = { selectedScreen = "inventory" },
                    icon = { Icon(Icons.Filled.Inventory, contentDescription = "Inventory") },
                    label = { Text("Inventory") }
                )
                NavigationBarItem(
                    selected = selectedScreen == "photos",
                    onClick = { selectedScreen = "photos" },
                    icon = { Icon(Icons.Filled.PhotoLibrary, contentDescription = "Photos") },
                    label = { Text("Photos") }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedScreen) {
                "inventory" -> InventoryScreen(viewModel = inventoryViewModel)
                "photos" -> PhotoListScreen(viewModel = photoViewModel)
            }
        }
    }
}
