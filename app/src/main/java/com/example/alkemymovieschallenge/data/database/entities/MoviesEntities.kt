package com.example.alkemymovieschallenge.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "movies_table")
data class MoviesEntities(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id:Int = 0,
    @ColumnInfo(name = "language") val language: String,
    @ColumnInfo(name = "page") val page: String,
    @ColumnInfo(name = "region") val region: String
)
/*fun DomainModel.toDataBase() = MoviesEntities(drinks = drinks,
                                             category = category,
                                             alcohol = alcohol,
                                             instructions = instructions,
                                             image = image)*/
