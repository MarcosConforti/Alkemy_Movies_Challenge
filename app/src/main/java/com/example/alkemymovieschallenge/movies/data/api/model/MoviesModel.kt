package com.example.alkemymovieschallenge.movies.data.api.model

import com.google.gson.annotations.SerializedName


data class MoviesModel(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val image: String
)
