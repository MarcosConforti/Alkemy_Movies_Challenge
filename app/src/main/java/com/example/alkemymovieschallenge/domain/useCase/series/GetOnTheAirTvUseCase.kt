package com.example.alkemymovieschallenge.domain.useCase.series

import com.example.alkemymovieschallenge.data.repository.SeriesRepository
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOnTheAirTvUseCase @Inject constructor(private val seriesRepository: SeriesRepository) {

    operator fun invoke(): Flow<NetworkState<List<DomainModel>>> =
        seriesRepository.getOnTheAirTvFromApi()
}
