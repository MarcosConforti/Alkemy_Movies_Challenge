package com.example.alkemymovieschallenge.domain.useCase.favorites

import com.example.alkemymovieschallenge.data.repository.FavoritesRepository
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainFavoritesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(private val repository: FavoritesRepository) {

    operator fun invoke(): Flow<NetworkState<List<DomainFavoritesModel>>> =
        repository.getFavorites()

}