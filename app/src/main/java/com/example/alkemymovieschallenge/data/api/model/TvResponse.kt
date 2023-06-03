package com.example.alkemymovieschallenge.data.api.model

import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("results") val results: List<TvModel>
)
