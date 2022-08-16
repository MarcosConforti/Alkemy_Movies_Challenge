package com.example.alkemymovieschallenge.data.model

import com.google.gson.annotations.SerializedName

data class MoviesModel(
    @SerializedName("language") val language: String,
    @SerializedName("page") val page: String,
    @SerializedName("region") val region: String
)
