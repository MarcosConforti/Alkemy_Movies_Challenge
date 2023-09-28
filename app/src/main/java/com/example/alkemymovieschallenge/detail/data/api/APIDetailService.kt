package com.example.alkemymovieschallenge.detail.data.api

import com.example.alkemymovieschallenge.movies.data.api.model.MoviesModel
import com.example.alkemymovieschallenge.series.data.api.model.SeriesModel
import retrofit2.http.GET
import retrofit2.http.Path

interface APIDetailService {

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId:String): MoviesModel

    @GET("3/tv/{series_id}")
    suspend fun getSeriesDetail(@Path("series_id") seriesId:String): SeriesModel
}