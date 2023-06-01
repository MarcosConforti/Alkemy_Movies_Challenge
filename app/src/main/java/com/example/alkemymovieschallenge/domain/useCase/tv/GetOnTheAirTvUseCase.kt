package com.example.alkemymovieschallenge.domain.useCase.tv

import com.example.alkemymovieschallenge.data.TvRepository
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import javax.inject.Inject

class GetOnTheAirTvUseCase @Inject constructor(private val tvRepository: TvRepository) {

    suspend operator fun invoke(): NetworkState<List<DomainTvModel>> =
        tvRepository.getOnTheAirTvFromApi()
}
