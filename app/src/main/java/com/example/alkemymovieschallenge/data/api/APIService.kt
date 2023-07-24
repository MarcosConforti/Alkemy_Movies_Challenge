package com.example.alkemymovieschallenge.data.api

import com.example.alkemymovieschallenge.data.api.model.MoviesResponse
import com.example.alkemymovieschallenge.data.api.model.SeriesResponse
import retrofit2.http.GET

interface APIService {
      //Movies
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

       //Series
    @GET("3/tv/popular")
    suspend fun getPopularTv(): SeriesResponse

    @GET("3/tv/top_rated")
    suspend fun getTopRatedTv(): SeriesResponse

    @GET("3/tv/airing_today")
    suspend fun getAiringTodayTv(): SeriesResponse

    @GET("3/tv/on_the_air")
    suspend fun getOnTheAirTv(): SeriesResponse
}