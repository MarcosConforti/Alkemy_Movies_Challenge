package com.example.alkemymovieschallenge.favorites.domain.useCase

import com.example.alkemymovieschallenge.favorites.data.repository.FavoritesRepository
import com.example.alkemymovieschallenge.core.domain.NetworkState
import com.example.alkemymovieschallenge.core.domain.DomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(private val repository: FavoritesRepository) {

    operator fun invoke(): Flow<NetworkState<List<DomainModel>>> =
        repository.getFavorites()

}