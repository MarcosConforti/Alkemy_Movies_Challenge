package com.example.alkemymovieschallenge.data.network

import com.example.alkemymovieschallenge.data.model.MoviesResponse
import com.example.alkemymovieschallenge.data.model.TvResponse
import retrofit2.http.GET

interface APIService {
      //Movies
    @GET("3/movie/popular?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getPopularMovies(): MoviesResponse

    @GET("3/movie/top_rated?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getTopRatedtMovies():MoviesResponse

    @GET("3/movie/upcoming?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getUpComingMovies():MoviesResponse

    @GET("3/movie/now_playing?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getNowPlayingMovies():MoviesResponse

       //Series
    @GET("3/tv/popular?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getPopularTv():TvResponse

    @GET("3/tv/top_rated?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getTopRatedTv():TvResponse

    @GET("3/tv/airing_today?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getAiringTodayTv():TvResponse

    @GET("3/tv/on_the_air?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getOnTheAirTv():TvResponse

    @GET("3/search/movie?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getAllMovies():MoviesResponse


}