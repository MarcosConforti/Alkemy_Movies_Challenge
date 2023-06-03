package com.example.alkemymovieschallenge.domain.model

import android.os.Parcelable
import com.example.alkemymovieschallenge.data.database.entities.FavoritesEntities
import com.example.alkemymovieschallenge.data.api.model.FavoritesModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainFavoritesModel(
    var id:Int = 0,
    val title: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview:String,
    val image: String
):Parcelable
//funcion de extension para realizar los mapers
fun FavoritesModel.toDomainFavorites() = DomainFavoritesModel(id.length,title, voteAverage,releaseDate,overview,image)
fun DomainFavoritesModel.toFavoritesEntities() = FavoritesEntities(id,title, voteAverage,releaseDate,overview, image)
