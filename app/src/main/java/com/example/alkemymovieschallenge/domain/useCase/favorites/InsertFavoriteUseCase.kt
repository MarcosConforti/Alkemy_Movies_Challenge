package com.example.alkemymovieschallenge.domain.useCase.favorites

import com.example.alkemymovieschallenge.data.MoviesRepository
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import javax.inject.Inject

class InsertFavoriteUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend fun insertMovie(movie: MoviesEntities) = moviesRepository.insertOnlyMovie(movie)

}