package com.example.alkemymovieschallenge.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvModel(
    @SerializedName("name") val title: String,
    @SerializedName("first_air_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val image: String
):Parcelable
