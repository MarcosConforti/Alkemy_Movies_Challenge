package com.example.alkemymovieschallenge.ui.model

import android.os.Parcelable
import com.example.alkemymovieschallenge.data.database.entities.SeriesEntities
import com.example.alkemymovieschallenge.domain.model.DomainModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UIModel(
    var id:Int = 0,
    val title: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview:String,
    val image: String
) : Parcelable

fun DomainModel.toUIModel() = UIModel(id,title, voteAverage,releaseDate,overview,image)
fun SeriesEntities.toDomainModel() = DomainModel(id,title, voteAverage,releaseDate,overview,image)

