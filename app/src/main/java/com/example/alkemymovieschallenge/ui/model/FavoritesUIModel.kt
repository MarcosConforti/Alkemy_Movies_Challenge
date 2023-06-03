package com.example.alkemymovieschallenge.ui.model

import android.os.Parcelable
import com.example.alkemymovieschallenge.data.database.entities.FavoritesEntities
import com.example.alkemymovieschallenge.data.api.model.FavoritesModel
import com.example.alkemymovieschallenge.domain.model.DomainFavoritesModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoritesUIModel(
    var id:Int = 0,
    val title: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview:String,
    val image: String
):Parcelable
//funcion de extension para realizar los mapers
fun DomainFavoritesModel.toUIFavorites() = FavoritesUIModel(id,title, voteAverage,releaseDate,overview,image)
fun FavoritesEntities.toDomainFavorites() = DomainFavoritesModel(id,title, voteAverage,releaseDate,overview, image)
