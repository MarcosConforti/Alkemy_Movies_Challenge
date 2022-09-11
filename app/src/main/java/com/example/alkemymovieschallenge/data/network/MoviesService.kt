package com.example.alkemymovieschallenge.data.network

import com.example.alkemymovieschallenge.data.model.MoviesModel

import kotlinx.coroutines.*
import javax.inject.Inject

class MoviesService @Inject constructor(private val apiService: APIService) {

    suspend fun getPopularMovies():List<MoviesModel> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getPopularMovies()
            response.body()?.results ?: emptyList()
        }
    }
    suspend fun getLatestMovies():List<MoviesModel> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getLatestMovies()
            response.body()?.results?: emptyList()
        }
    }
    suspend fun getTopRatedMovies():List<MoviesModel> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getTopRatedtMovies()
            response.body()?.results?: emptyList()
        }
    }
    suspend fun getUpComingMovies():List<MoviesModel> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getUpComingMovies()
            response.body()?.results?: emptyList()
        }
    }
    suspend fun getNowPlayingMovies():List<MoviesModel> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getNowPlayingMovies()
            response.body()?.results?: emptyList()
        }
    }
}