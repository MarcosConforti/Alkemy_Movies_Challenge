package com.example.alkemymovieschallenge.domain.useCase.movies

import com.example.alkemymovieschallenge.data.repository.MoviesRepository
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    operator fun invoke(): Flow<NetworkState<List<DomainModel>>> =
        moviesRepository.getNowPlayingMoviesFromApi()

}
