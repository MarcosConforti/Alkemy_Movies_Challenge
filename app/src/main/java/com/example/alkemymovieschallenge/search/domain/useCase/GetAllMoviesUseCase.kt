package com.example.alkemymovieschallenge.search.domain.useCase

import com.example.alkemymovieschallenge.movies.data.repository.MoviesRepository
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.core.domain.DomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    operator fun invoke(): Flow<NetworkState<List<DomainModel>>> =
        moviesRepository.getAllMoviesFromApi()
}
