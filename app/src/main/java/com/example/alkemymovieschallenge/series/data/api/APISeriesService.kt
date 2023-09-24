package com.example.alkemymovieschallenge.series.data.api

import com.example.alkemymovieschallenge.series.data.api.model.SeriesResponse
import retrofit2.http.GET

interface APISeriesService {

    @GET("3/tv/popular")
    suspend fun getPopularTv(): SeriesResponse

    @GET("3/tv/top_rated")
    suspend fun getTopRatedTv(): SeriesResponse

    @GET("3/tv/airing_today")
    suspend fun getAiringTodayTv(): SeriesResponse

    @GET("3/tv/on_the_air")
    suspend fun getOnTheAirTv(): SeriesResponse
}