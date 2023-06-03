package com.example.alkemymovieschallenge.domain.model

import android.os.Parcelable
import com.example.alkemymovieschallenge.data.database.entities.TvEntities
import com.example.alkemymovieschallenge.data.api.model.TvModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainTvModel(
    var id:Int = 0,
    val title: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview:String,
    val image: String
):Parcelable

//funcion de extension para realizar los mapers
fun TvModel.toDomainTv() = DomainTvModel(id.length,title, voteAverage,releaseDate,overview,image)
fun TvEntities.toDomainTv() = DomainTvModel(id,title, voteAverage,releaseDate,overview, image)
