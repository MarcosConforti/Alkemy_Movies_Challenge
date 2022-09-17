package com.example.alkemymovieschallenge.domain.model

import android.os.Parcelable
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import com.example.alkemymovieschallenge.data.model.MoviesModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainModel(
    var id:Int = 0,
    val title: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview:String,
    val image: String
):Parcelable
//funcion de extension para realizar los mapers
fun MoviesModel.toDomainMovie() = DomainModel(id.length,title, voteAverage,releaseDate,overview,image)
fun MoviesEntities.toDomainMovie() = DomainModel(id,title, voteAverage,releaseDate,overview, image)
