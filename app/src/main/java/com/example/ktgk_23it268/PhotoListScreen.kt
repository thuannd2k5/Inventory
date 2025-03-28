package com.example.ktgk_23it268

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PhotoListScreen(viewModel: PhotoViewModel = viewModel()) {
    val photos by viewModel.photoList.collectAsStateWithLifecycle(initialValue = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Photo",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(photos) { photo ->
                PhotoItem(photo)
            }
        }
    }
}

@Composable
fun PhotoItem(photo: Photo) {

    var expanded by rememberSaveable{ mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = MaterialTheme.shapes.medium, // Bo góc card đẹp hơn
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Thêm đổ bóng nhẹ
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${photo.id}",fontSize = 20.sp,fontWeight = FontWeight.Bold)
            Text(text = "URL: ${photo.url}", color = MaterialTheme.colorScheme.primary,fontSize = 18.sp)
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Title: ${photo.title}", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
