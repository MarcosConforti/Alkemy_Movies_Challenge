package com.example.alkemymovieschallenge.domain.useCase.search

import com.example.alkemymovieschallenge.data.repository.MoviesRepository
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainMoviesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(): Flow<NetworkState<List<DomainMoviesModel>>> =
        moviesRepository.getAllMoviesFromApi()
}
