package com.example.alkemymovieschallenge.domain.useCase.movies

import com.example.alkemymovieschallenge.data.repository.MoviesRepository
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlternativeTitlesUseCase @Inject constructor(private val repository: MoviesRepository) {

    fun getAlternativeTitles(id : String): Flow<NetworkState<List<DomainModel>>> =
        repository.getAlternativeTitles(id)
}