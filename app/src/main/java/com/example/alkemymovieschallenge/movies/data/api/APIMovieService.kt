package com.example.alkemymovieschallenge.movies.data.api

import com.example.alkemymovieschallenge.movies.data.api.model.MoviesResponse
import retrofit2.http.GET

interface APIMovieService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(): MoviesResponse

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(): MoviesResponse

    @GET("3/movie/upcoming")
    suspend fun getUpComingMovies(): MoviesResponse

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(): MoviesResponse

    @GET("3/search/movie")
    suspend fun getAllMovies(): MoviesResponse

}