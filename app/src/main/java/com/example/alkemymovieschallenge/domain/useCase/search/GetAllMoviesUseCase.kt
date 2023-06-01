package com.example.alkemymovieschallenge.domain.useCase.search

import com.example.alkemymovieschallenge.data.MoviesRepository
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(): NetworkState<List<DomainModel>> =
        moviesRepository.getAllMoviesFromApi()



}
