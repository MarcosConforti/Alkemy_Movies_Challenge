package com.example.alkemymovieschallenge.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesModel(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val image: String,
  //  @SerializedName("type") val type: String
):Parcelable
