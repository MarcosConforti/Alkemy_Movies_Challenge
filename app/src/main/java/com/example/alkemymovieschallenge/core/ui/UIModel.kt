package com.example.alkemymovieschallenge.core.ui

import com.example.alkemymovieschallenge.core.domain.DomainModel
import com.example.alkemymovieschallenge.series.data.database.SeriesEntities


data class UIModel(
    var id:String,
    val title: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview:String,
    val image: String
)

fun DomainModel.toUIModel() = UIModel(id,title, voteAverage,releaseDate,overview,image)
fun SeriesEntities.toDomainModel() = DomainModel(id.toString(),title, voteAverage,releaseDate,overview,image)

