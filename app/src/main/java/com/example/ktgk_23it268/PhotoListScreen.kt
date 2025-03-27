package com.example.ktgk_23it268

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PhotoListScreen(viewModel: PhotoViewModel = viewModel()) {
    val photos by viewModel.photoList.collectAsStateWithLifecycle(initialValue = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(82.dp)) // Dịch tiêu đề xuống
        Text(
            text = "Photo URLs from API",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 12.dp)
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = MaterialTheme.shapes.medium, // Bo góc card đẹp hơn
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Thêm đổ bóng nhẹ
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${photo.id}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Title: ${photo.title}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "URL: ${photo.url}", color = MaterialTheme.colorScheme.primary)
        }
    }
}
