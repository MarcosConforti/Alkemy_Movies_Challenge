package com.example.alkemymovieschallenge.series.data.api.model

import com.google.gson.annotations.SerializedName

data class SeriesResponse(
    @SerializedName("results") val results: List<SeriesModel>
)
