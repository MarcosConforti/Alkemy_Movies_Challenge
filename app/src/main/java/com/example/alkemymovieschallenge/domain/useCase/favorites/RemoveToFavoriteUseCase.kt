package com.example.alkemymovieschallenge.domain.useCase.favorites

import com.example.alkemymovieschallenge.data.repository.FavoritesRepository
import javax.inject.Inject

class RemoveToFavoriteUseCase @Inject constructor(private val repository: FavoritesRepository){
    suspend fun removeToFavorites(title: String) = repository.cleanList(title)
}