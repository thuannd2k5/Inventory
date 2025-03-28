package com.example.ktgk_23it268

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Photo(
    val id: Int,
    val title: String,
    val url: String
)

interface PhotoApiService {
    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}

class PhotoViewModel : ViewModel() {
    private val _photoList = MutableStateFlow<List<Photo>>(emptyList())
    val photoList: StateFlow<List<Photo>> = _photoList

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(PhotoApiService::class.java)

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            try {
                _photoList.value = apiService.getPhotos()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
