package com.example.alkemymovieschallenge.core.domain

import com.example.alkemymovieschallenge.favorites.data.database.FavoritesEntities
import com.example.alkemymovieschallenge.movies.data.api.model.MoviesModel
import com.example.alkemymovieschallenge.movies.data.database.MoviesEntities
import com.example.alkemymovieschallenge.series.data.api.model.SeriesModel
import com.example.alkemymovieschallenge.series.data.database.SeriesEntities

data class DomainModel(
    var id: String,
    val title: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview: String,
    val image: String
)

fun MoviesModel.toDomainModel() = DomainModel(id,title, voteAverage,releaseDate,overview,image)
fun MoviesEntities.toDomainModel() = DomainModel(id.toString(),title, voteAverage,releaseDate,overview, image)

fun SeriesModel.toDomainModel() = DomainModel(id,title, voteAverage,releaseDate,overview,image)
fun SeriesEntities.toDomainModel() = DomainModel(id.toString(),title, voteAverage,releaseDate,overview, image)

fun DomainModel.toFavoritesEntities() = FavoritesEntities(id.length,title, voteAverage,releaseDate,overview,image)
fun FavoritesEntities.toDomainModel() = DomainModel(id.toString(),title, voteAverage,releaseDate,overview, image)

