package com.example.alkemymovieschallenge.domain.useCase.tv

import com.example.alkemymovieschallenge.data.repository.TvRepository
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedTvUseCase @Inject constructor(private val tvRepository: TvRepository) {

    suspend operator fun invoke(): Flow<NetworkState<List<DomainTvModel>>> =
        tvRepository.getTopRatedTvFromApi()
}
