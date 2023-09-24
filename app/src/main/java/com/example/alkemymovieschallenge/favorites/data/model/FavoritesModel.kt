package com.example.alkemymovieschallenge.favorites.data.model

import com.google.gson.annotations.SerializedName


data class FavoritesModel(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val image: String
)
