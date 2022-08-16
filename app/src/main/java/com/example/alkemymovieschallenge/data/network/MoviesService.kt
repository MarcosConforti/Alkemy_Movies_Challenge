package com.example.alkemymovieschallenge.data.network

import com.example.alkemymovieschallenge.data.model.MoviesModel
import com.example.alkemymovieschallenge.data.network.APIService
import kotlinx.coroutines.*
import javax.inject.Inject

class MoviesService @Inject constructor(private val apiService: APIService) {

    suspend fun getMovies() {
        return withContext(Dispatchers.IO) {
            val response = apiService.getAllMovies()
        }
    }
}