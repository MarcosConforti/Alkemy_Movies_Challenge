package com.example.alkemymovieschallenge.domain.useCase.favorites

import com.example.alkemymovieschallenge.data.repository.FavoritesRepository
import com.example.alkemymovieschallenge.data.database.entities.FavoritesEntities
import javax.inject.Inject

class InsertFavoriteUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {

    suspend fun addToFavorites(favorites: FavoritesEntities) =
        favoritesRepository.addToFavorites(favorites)

    suspend fun checkFavorites(id:String) = favoritesRepository.checkFavorite(id)

    suspend fun removeToFavorites(id:String) = favoritesRepository.cleanList(id)

}