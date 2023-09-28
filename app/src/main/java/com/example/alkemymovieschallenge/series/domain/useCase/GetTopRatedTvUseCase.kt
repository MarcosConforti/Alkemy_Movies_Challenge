package com.example.alkemymovieschallenge.series.domain.useCase

import com.example.alkemymovieschallenge.series.data.repository.SeriesRepository
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.core.domain.DomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedTvUseCase @Inject constructor(private val seriesRepository: SeriesRepository) {

    operator fun invoke(): Flow<NetworkState<List<DomainModel>>> =
        seriesRepository.getTopRatedTvFromApi()
}
