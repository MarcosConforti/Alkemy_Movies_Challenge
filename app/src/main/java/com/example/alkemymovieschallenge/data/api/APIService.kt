package com.example.alkemymovieschallenge.data.api

import com.example.alkemymovieschallenge.data.api.model.MoviesResponse
import com.example.alkemymovieschallenge.data.api.model.SeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
      //Movies
    @GET("3/movie/popular?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getPopularMovies(): MoviesResponse

    @GET("3/movie/top_rated?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getTopRatedMovies(): MoviesResponse

    @GET("3/movie/upcoming?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getUpComingMovies(): MoviesResponse

    @GET("3/movie/now_playing?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getNowPlayingMovies(): MoviesResponse

    @GET("3/search/movie?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getAllMovies(): MoviesResponse

    @GET("/3/movie/{movie_id}/similar?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getAlternativeTitles(@Path("movie_id") movieId: String): MoviesResponse

       //Series
    @GET("3/tv/popular?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getPopularTv(): SeriesResponse

    @GET("3/tv/top_rated?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getTopRatedTv(): SeriesResponse

    @GET("3/tv/airing_today?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getAiringTodayTv(): SeriesResponse

    @GET("3/tv/on_the_air?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getOnTheAirTv(): SeriesResponse




}