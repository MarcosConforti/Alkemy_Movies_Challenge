package com.example.alkemymovieschallenge.ui.model

import android.os.Parcelable
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import com.example.alkemymovieschallenge.domain.model.DomainMoviesModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesUIModel(
    var id:Int = 0,
    val title: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview:String,
    val image: String
):Parcelable
//funcion de extension para realizar los mapers
fun DomainMoviesModel.toUIMovie() = MoviesUIModel(id,title, voteAverage,releaseDate,overview,image)
fun MoviesEntities.toDomainMovie() = DomainMoviesModel(id,title, voteAverage,releaseDate,overview, image)
