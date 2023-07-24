package com.example.alkemymovieschallenge.domain.model

import android.os.Parcelable
import com.example.alkemymovieschallenge.data.api.model.MoviesModel
import com.example.alkemymovieschallenge.data.api.model.SeriesModel
import com.example.alkemymovieschallenge.data.database.entities.FavoritesEntities
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import com.example.alkemymovieschallenge.data.database.entities.SeriesEntities
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainModel(
    var id: Int = 0,
    val title: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview: String,
    val image: String
) : Parcelable

fun MoviesModel.toDomainModel() = DomainModel(id.length,title, voteAverage,releaseDate,overview,image)
fun MoviesEntities.toDomainModel() = DomainModel(id,title, voteAverage,releaseDate,overview, image)

fun SeriesModel.toDomainModel() = DomainModel(id.length,title, voteAverage,releaseDate,overview,image)
fun SeriesEntities.toDomainModel() = DomainModel(id,title, voteAverage,releaseDate,overview, image)

fun DomainModel.toFavoritesEntities() = FavoritesEntities(id,title, voteAverage,releaseDate,overview,image)
fun FavoritesEntities.toDomainModel() = DomainModel(id,title, voteAverage,releaseDate,overview, image)

