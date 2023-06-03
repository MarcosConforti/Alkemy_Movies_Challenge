package com.example.alkemymovieschallenge.domain.model

import android.os.Parcelable
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import com.example.alkemymovieschallenge.data.api.model.MoviesModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainMoviesModel(
    var id:Int = 0,
    val title: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview:String,
    val image: String
):Parcelable
//funcion de extension para realizar los mapers
fun MoviesModel.toDomainMovie() = DomainMoviesModel(id.length,title, voteAverage,releaseDate,overview,image)
fun MoviesEntities.toDomainMovie() = DomainMoviesModel(id,title, voteAverage,releaseDate,overview, image)
