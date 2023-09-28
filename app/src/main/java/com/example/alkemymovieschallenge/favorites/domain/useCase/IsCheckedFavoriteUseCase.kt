package com.example.alkemymovieschallenge.favorites.domain.useCase

import com.example.alkemymovieschallenge.favorites.data.repository.FavoritesRepository
import javax.inject.Inject

class IsCheckedFavoriteUseCase @Inject constructor(private val repository: FavoritesRepository) {

     suspend fun checkFavorites(title:String):Boolean =
        repository.checkFavorite(title)
}