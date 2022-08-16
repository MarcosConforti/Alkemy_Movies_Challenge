package com.example.alkemymovieschallenge.data.network

import com.example.alkemymovieschallenge.data.model.MoviesModel
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("search.php?s=")
    suspend fun getAllMovies(): Response<MoviesModel>
}