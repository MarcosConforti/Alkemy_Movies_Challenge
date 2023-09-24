package com.example.alkemymovieschallenge.series.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeriesModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val title: String,
    @SerializedName("first_air_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val image: String
):Parcelable
