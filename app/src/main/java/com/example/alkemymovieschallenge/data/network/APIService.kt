package com.example.alkemymovieschallenge.data.network

import com.example.alkemymovieschallenge.data.model.MoviesModel
import com.example.alkemymovieschallenge.data.model.MoviesResponse
import com.example.alkemymovieschallenge.data.model.TvResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("3/movie/popular?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getPopularMovies(): Response<MoviesResponse>

    @GET("3/movie/popular?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getLatestMovies():Response<MoviesResponse>

    @GET("3/movie/top_rated?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getTopRatedtMovies():Response<MoviesResponse>

    @GET("3/movie/upcoming?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getUpComingMovies():Response<MoviesResponse>

    @GET("3/movie/now_playing?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getNowPlayingMovies():Response<MoviesResponse>

    @GET("3/tv/popular?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getPopularTv():Response<TvResponse>

    @GET("3/tv/popular?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getLatestTv():Response<TvResponse>

    @GET("3/tv/top_rated?api_key=b1bc4f98ee6343c991e3ee0d83868679")
    suspend fun getTopRatedTv():Response<TvResponse>


}