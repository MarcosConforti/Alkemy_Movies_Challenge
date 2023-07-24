package com.example.alkemymovieschallenge.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.alkemymovieschallenge.data.api.model.SeriesModel

@Entity(tableName = "series_table")
data class SeriesEntities(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "vote_average") val voteAverage: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "image") val image: String
)

fun SeriesModel.toSeriesDataBase() = SeriesEntities(
    id = id.toInt(),
    title = title,
    voteAverage = voteAverage,
    overview = overview,
    releaseDate = releaseDate,
    image = image
)
