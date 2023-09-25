package com.example.alkemymovieschallenge.series.data.api.model

import com.google.gson.annotations.SerializedName

data class SeriesModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val title: String,
    @SerializedName("first_air_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("backdrop_path") val image: String
)
