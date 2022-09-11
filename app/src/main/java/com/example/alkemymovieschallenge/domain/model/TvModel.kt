package com.example.alkemymovieschallenge.domain.model

import android.os.Parcelable
import com.example.alkemymovieschallenge.data.database.entities.TvEntities
import com.example.alkemymovieschallenge.data.model.TvModel
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainTvModel(
    val title: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview:String,
    val image: String
):Parcelable

//funcion de extension para realizar los mapers
fun TvModel.toDomainTv() = DomainTvModel(title, voteAverage,releaseDate,overview,image)
fun TvEntities.toDomainTv() = DomainTvModel(title, voteAverage,releaseDate,overview, image)
