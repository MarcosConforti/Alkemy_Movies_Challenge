package com.example.alkemymovieschallenge.favorites.domain.useCase

import com.example.alkemymovieschallenge.favorites.data.repository.FavoritesRepository
import javax.inject.Inject

class RemoveToFavoriteUseCase @Inject constructor(private val repository: FavoritesRepository){
    suspend fun removeToFavorites(title: String) = repository.cleanList(title)
}