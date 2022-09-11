package com.example.alkemymovieschallenge.data.network

import com.example.alkemymovieschallenge.data.model.TvModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvService @Inject constructor(private val apiService: APIService) {

    suspend fun getPopularTv():List<TvModel> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getPopularTv()
            response.body()?.results ?: emptyList()
        }
    }
    suspend fun getLatestTv():List<TvModel> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getLatestTv()
            response.body()?.results ?: emptyList()
        }
    }
    suspend fun getTopRatedTV():List<TvModel> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getTopRatedTv()
            response.body()?.results?: emptyList()
        }
    }

}