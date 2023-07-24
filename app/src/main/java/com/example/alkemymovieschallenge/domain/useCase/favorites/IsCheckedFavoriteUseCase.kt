package com.example.alkemymovieschallenge.domain.useCase.favorites

import com.example.alkemymovieschallenge.data.repository.FavoritesRepository
import javax.inject.Inject

class IsCheckedFavoriteUseCase @Inject constructor(private val repository: FavoritesRepository) {

     suspend fun checkFavorites(title:String):Boolean =
        repository.checkFavorite(title)
}