package com.example.alkemymovieschallenge.domain.useCase.favorites

import com.example.alkemymovieschallenge.data.repository.FavoritesRepository
import com.example.alkemymovieschallenge.domain.model.DomainModel
import javax.inject.Inject

class InsertFavoriteUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    suspend fun addToFavorites(favorites: DomainModel) =
        favoritesRepository.addToFavorites(favorites)
}