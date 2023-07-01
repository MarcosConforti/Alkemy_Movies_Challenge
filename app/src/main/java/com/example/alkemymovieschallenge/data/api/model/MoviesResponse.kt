package com.example.alkemymovieschallenge.data.api.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results") val results: List<MoviesModel>
)
/*data class AlternativeTitleResponse(
    @SerializedName("titles") val titles: List<MoviesModel>)*/
