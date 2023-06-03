package com.example.alkemymovieschallenge.domain.useCase.favorites

import com.example.alkemymovieschallenge.data.repository.FavoritesRepository
import com.example.alkemymovieschallenge.domain.model.DomainFavoritesModel
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(private val repository: FavoritesRepository) {

    suspend operator fun invoke(): List<DomainFavoritesModel> =
        repository.getFavorites()
}