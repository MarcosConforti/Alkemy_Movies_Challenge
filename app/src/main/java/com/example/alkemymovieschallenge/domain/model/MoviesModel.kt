package com.example.alkemymovieschallenge.domain.model

import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import com.example.alkemymovieschallenge.data.model.MoviesModel

data class DomainModel(
    val language: String,
    val page: String,
    val region: String
)
//funcion de extension para realizar los mapers
fun MoviesModel.toDomain() = DomainModel(language, page, region)
fun MoviesEntities.toDomain() = DomainModel(language, page, region)
