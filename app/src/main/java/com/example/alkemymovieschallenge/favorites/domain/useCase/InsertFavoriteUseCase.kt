package com.example.alkemymovieschallenge.favorites.domain.useCase

import com.example.alkemymovieschallenge.favorites.data.repository.FavoritesRepository
import com.example.alkemymovieschallenge.core.domain.DomainModel
import javax.inject.Inject

class InsertFavoriteUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    suspend fun addToFavorites(favorites: DomainModel) =
        favoritesRepository.addToFavorites(favorites)
}