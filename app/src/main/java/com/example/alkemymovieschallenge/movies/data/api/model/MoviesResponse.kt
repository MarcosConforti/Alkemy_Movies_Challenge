package com.example.alkemymovieschallenge.movies.data.api.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results") val results: List<MoviesModel>
)
